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
import pl.coderslab.charity.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {

    UserService userService;
    ApplicationEventPublisher eventPublisher;
    EmailServiceImpl emailService;

    public RegistrationController(UserService userService,
                                  ApplicationEventPublisher eventPublisher,
                                  EmailServiceImpl emailService) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.emailService = emailService;
    }

    @GetMapping
    public String register(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }

        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "user_admin/register";
    }

    @PostMapping
    public String registering(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                              BindingResult result) {
        if (result.hasErrors() && (userDTO.getPassword().equals(userDTO.getRePassword()))) {
            return "user_admin/register";
        } else if (result.hasErrors() || (!userDTO.getPassword().equals(userDTO.getRePassword()))) {
            result.rejectValue("rePassword", null, "Hasła się różnią");
            return "user_admin/register";
        } else {
           VerificationToken verificationToken = userService.register(userDTO);
           String message = "http://localhost:8080/activate?token="+verificationToken.getToken();
           emailService.sendSimpleMessage(userDTO.getEmail(),"account activation",message);
           return "user_admin/registrationLinkSent";
        }

    }
}
