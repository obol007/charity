package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.domain.model.VerificationToken;
import pl.coderslab.charity.domain.repository.TokenRepository;
import pl.coderslab.charity.mail.EmailServiceImpl;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/activate")
@Slf4j
public class ActivationController {

    UserService userService;
    TokenRepository tokenRepository;
    EmailServiceImpl emailService;

    public ActivationController(UserService userService,
                                TokenRepository tokenRepository,
                                EmailServiceImpl emailService) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    @GetMapping
    public String activate(@RequestParam String token) {

        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "user_admin/registration/register";
        } else if (LocalDateTime.now().isBefore(verificationToken.getExpiryDate())) {
            userService.activate(verificationToken);
            return "user_admin/registration/registrationConf";
        } else {
            String newToken = userService.generateNewTokenByEmail(verificationToken.getUser().getEmail());
            String message = "http://localhost:8080/activate?token="+newToken;
            emailService.sendSimpleMessage(verificationToken.getUser().getEmail(),
                    "registration",message);
            return "user_admin/registration/reRegistration";

        }
    }
}
