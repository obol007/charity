package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.ResetPasswordDTO;
import pl.coderslab.charity.domain.model.VerificationToken;
import pl.coderslab.charity.domain.repository.TokenRepository;
import pl.coderslab.charity.mail.EmailServiceImpl;
import pl.coderslab.charity.service.MailboxService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@Slf4j
@RequestMapping("/resetPassword")
public class ResetPasswordController {

    UserService userService;
    EmailServiceImpl emailService;
    TokenRepository tokenRepository;
    PasswordEncoder passwordEncoder;
    MailboxService mailboxService;

    public ResetPasswordController(UserService userService,
                                   EmailServiceImpl emailService,
                                   TokenRepository tokenRepository,
                                   PasswordEncoder passwordEncoder,
                                   MailboxService mailboxService) {
        this.userService = userService;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailboxService = mailboxService;
    }

    @GetMapping
    public String resetPassword(Model model) {
        model.addAttribute("resetPassDTO", new ResetPasswordDTO());
        return "user_admin/password/resetPassword";
    }

    @PostMapping
    public String resettingPassword(@Valid @ModelAttribute("resetPassDTO") ResetPasswordDTO resetPasswordDTO, Model model, BindingResult result) {
        if(result.hasErrors()){
            return "user_admin/password/resetPassword";
        }
        String email = resetPasswordDTO.getEmail();

        ResetPasswordDTO resetPassword = userService.findUserToResetPassword(email);
         if(resetPassword.getEmail()==null){
            result.rejectValue("email", null, "Brak użytkownika w bazie danych");
            return "user_admin/password/resetPassword";
        }
        if(resetPassword.getBlocked()){
            result.rejectValue("email", null, "Użytkownik jest zablokowany. Prosimy o kontakt");
            return "user_admin/password/resetPassword";
        }

        if(!resetPassword.getRegistered()){
            result.rejectValue("email", null, "Użytkownik nie dokończył rejestracji");
            return "user_admin/password/resetPassword";
        }
        if(!resetPassword.getActive()&&resetPassword.getRegistered()){
            VerificationToken verificationToken = tokenRepository.findByUserId(resetPassword.getId());
            if(LocalDateTime.now().isAfter(verificationToken.getExpiryDate())){
                String updatedVerificationToken = userService.generateNewTokenByEmail(email);
                String message = "https://charity-coderslab.herokuapp.com/resetPassword/newPassword?token=" + updatedVerificationToken;
                mailboxService.send(email,message,"Reset password");
//                emailService.sendSimpleMessage(email, "reset password", message);
                model.addAttribute("newMessage",1);
                return "user_admin/password/resetPassReConfirmation";
            }else {
                result.rejectValue("email", null, "Wysłano maila z linkiem aktywacyjnym");

                return "user_admin/password/resetPassword";
            }
        }
        if(!resetPassword.getActive()&&!resetPassword.getRegistered()){

            return "user_admin/registration/registrationToComplete";
        }
         if (resetPassword.getActive() && !resetPassword.getBlocked()) {
            String verificationToken = userService.generateTokenByEmail(email);
            String message = "https://charity-coderslab.herokuapp.com/resetPassword/newPassword?token=" + verificationToken;
            mailboxService.send(email,message,"Reset password");
//            emailService.sendSimpleMessage(email, "reset password", message);
            model.addAttribute("newMessage",1);
            return "user_admin/password/resetPassConfirmation";
        }

        return "/";
    }

    @GetMapping("/newPassword")
    public String checkToken(@RequestParam String token, Model model) {

        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if(verificationToken==null) {
            return "user_admin/tokenExpired";
        }
         if(!verificationToken.getActive()) {
            return "user_admin/tokenExpired";
        }
        else if(LocalDateTime.now().isAfter(verificationToken.getExpiryDate())){
            return "user_admin/tokenExpired";
        }
        else {
            ResetPasswordDTO resetPasswordDTO = userService.findUserToResetPassword(verificationToken.getUser().getEmail());
            model.addAttribute("resetPassDTO", resetPasswordDTO);
            return "user_admin/password/newPassword";
        }
    }

    @PostMapping("/newPassword")
    public String saveNewPassword(@Valid @ModelAttribute("resetPassDTO") ResetPasswordDTO resetPasswordDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "user_admin/password/newPassword";

        } else if(!(resetPasswordDTO.getPassword().equals(resetPasswordDTO.getRePassword()))) {
            result.rejectValue("rePassword", null, "Nie wpisałeś tego samego hasła");
            return "user_admin/password/newPassword";
        } else {
            userService.resetPassword(resetPasswordDTO);
            return "user_admin/password/passChangeConf";
        }

    }


}
