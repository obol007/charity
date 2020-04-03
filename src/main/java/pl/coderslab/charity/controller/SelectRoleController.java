package pl.coderslab.charity.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/role")
public class SelectRoleController {

    public String selectRole(){
        String role = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        if(role.equals("[ROLE_ADMIN]")){
            return "redirect:/admin";
        }
        else return "redirect:/";
    }
}
