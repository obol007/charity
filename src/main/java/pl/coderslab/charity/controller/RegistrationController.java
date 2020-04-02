package pl.coderslab.charity.controller;

import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(Model model, Principal principal){
        if(principal!=null) {
            return "redirect:/";
        }


        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO",userDTO);
        return "register";
    }

    @PostMapping
    public String registering(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                              BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        userService.register(userDTO);
        return "redirect:/login";

    }
}
