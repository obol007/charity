package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {

    //TODO: o co chodzi ze sprawdzeniem principal?

    @GetMapping
    public String login(Principal principal){
            if(principal==null) {
                return "login";
            }
            return "/";
    }

}
