package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.domain.model.VerificationToken;
import pl.coderslab.charity.domain.repository.TokenRepository;
import pl.coderslab.charity.service.UserService;


@Controller
@RequestMapping("/activate")
@Slf4j
public class ActivationController {

    UserService userService;
    TokenRepository tokenRepository;

    public ActivationController(UserService userService,
                                TokenRepository tokenRepository) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping
    public String activate(@RequestParam String token){


        log.warn("Activation token: "+token);
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        log.warn("Verification token: "+verificationToken);
        if(verificationToken == null){
            return "user_admin/register";
        }else {
            userService.activate(verificationToken.getUser().getId());
            return "redirect:/login";
        }
    }
}
