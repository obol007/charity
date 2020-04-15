package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.repository.UserRepository;
import pl.coderslab.charity.service.UserService;


import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String login(Principal principal) {
        if (principal == null) {
            return "user_admin/login";
        } else if ((userService.findUserDTOByEmail(principal.getName()).getRole()).equals("ROLE_ADMIN")) {
        return "admin/admin";
        } else {
            return "redirect:/";
        }
    }
}

