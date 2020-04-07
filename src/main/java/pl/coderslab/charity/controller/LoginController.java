package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.repository.UserRepository;


import java.security.Principal;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String login(Principal principal, Model model) {
        if (principal == null) {
            return "user_admin/login";
        } else if ((userRepository.findByEmail(principal.getName()).getRole()).equals("ROLE_ADMIN")) {
            log.warn("ADMIN ROLE: "+userRepository.findByEmail(principal.getName()).getRole());
            return "admin/admin";
        } else {
            log.warn("USER ROLE: "+userRepository.findByEmail(principal.getName()).getRole());
            return "redirect:/";
        }
    }
}

