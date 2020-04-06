package pl.coderslab.charity.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    UserService userService;
    PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("loggedUser")
    public UserDTO loggedUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserDTOByEmail(email);
    }

    @GetMapping
    public String userPage(Model model){

        return "user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, EditUserDTO editUserDTO, Model model){
        editUserDTO = userService.findEditUserDTOById(id);
        model.addAttribute("userDTO",editUserDTO);
        return "userEdit";
    }
    @PostMapping("/edit")
    public String editingUser(@Valid @ModelAttribute("userDTO") EditUserDTO editUserDTO, BindingResult result){

        if(result.hasErrors() && (passwordEncoder.matches(editUserDTO.getOldPassword(),editUserDTO.getPassword()))){
            return "userEdit";
        } else if(result.hasErrors() || !passwordEncoder.matches(editUserDTO.getOldPassword(),editUserDTO.getPassword())){
            result.rejectValue("oldPassword",null,"Niepoprawnie wpisana stare hasło");
            return "userEdit";
        }
        else {
            log.warn("USERDTO: " + editUserDTO);
            userService.updateUser(editUserDTO);
            return "redirect:/user";
        }
    }
}
