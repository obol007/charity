package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.VerificationToken;
import pl.coderslab.charity.mail.EmailServiceImpl;
import pl.coderslab.charity.service.MailboxService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {

    UserService userService;
    ApplicationEventPublisher eventPublisher;
    EmailServiceImpl emailService;
    MailboxService mailboxService;

    public RegistrationController(UserService userService,
                                  ApplicationEventPublisher eventPublisher,
                                  EmailServiceImpl emailService,
                                  MailboxService mailboxService) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.emailService = emailService;
        this.mailboxService = mailboxService;
    }

    @GetMapping
    public String register(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }

        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "user_admin/registration/register";
    }

    @PostMapping
    public String registering(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                              BindingResult result,Model model) {
        if (result.hasErrors() && (userDTO.getPassword().equals(userDTO.getRePassword()))) {
            return "user_admin/registration/register";
        } else if (result.hasErrors() || (!userDTO.getPassword().equals(userDTO.getRePassword()))) {
            result.rejectValue("rePassword", null, "Hasła się różnią");
            return "user_admin/registration/register";
        } else {
            VerificationToken verificationToken = userService.register(userDTO);
            String message = "http://localhost:8080/activate?token="+verificationToken.getToken();
            mailboxService.send(userDTO.getEmail(),message, "Account activation");
//            emailService.sendSimpleMessage(userDTO.getEmail(),"account activation",message);
            model.addAttribute("newMessage",1);
            return "user_admin/registration/registrationLinkSent";
        }

    }

}
