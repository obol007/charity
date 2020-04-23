package pl.coderslab.charity.controller.commonControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.domain.model.VerificationToken;
import pl.coderslab.charity.domain.repository.TokenRepository;
import pl.coderslab.charity.mail.EmailServiceImpl;
import pl.coderslab.charity.service.MailboxService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@RequestMapping("/activate")
@Slf4j
public class ActivationController {

    UserService userService;
    TokenRepository tokenRepository;
    EmailServiceImpl emailService;
    MailboxService mailboxService;

    public ActivationController(UserService userService,
                                TokenRepository tokenRepository,
                                EmailServiceImpl emailService,
                                MailboxService mailboxService) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.mailboxService = mailboxService;
    }
    //przesylanie wartosci
    @Value("${app.host}")
    private String appHost;

    @Value("${server.port}")
    private String appPort;

    @Value("#{httpSession.id}")
    private String sessionId;

    @GetMapping
    public String activate(@RequestParam String token, Model model) {

        Optional<VerificationToken> optVerificationToken = Optional.ofNullable(tokenRepository.findByToken(token));
        if (optVerificationToken.isEmpty()) {
            return "user_admin/tokenExpired";
        }
        VerificationToken verificationToken = optVerificationToken.get();

        if (LocalDateTime.now().isBefore(verificationToken.getExpiryDate())) {
            userService.activate(verificationToken);
            return "user_admin/registration/registrationConf";
        } else {
            String newToken = userService.generateNewTokenByEmail(verificationToken.getUser().getEmail());
            String message = "http://localhost:8080/activate?token=" + newToken;
            emailService.sendSimpleMessage(verificationToken.getUser().getEmail(),
                    "registration", message);
            mailboxService.send(verificationToken.getUser().getEmail(), message, "New link to account activation");
            model.addAttribute("newMessage", 1);
            return "user_admin/registration/reRegistration";

        }
    }
}
