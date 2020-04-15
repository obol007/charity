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

    public RegistrationController(UserService userService,
                                  ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
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
                              BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors() && (userDTO.getPassword().equals(userDTO.getRePassword()))) {
            return "user_admin/register";
        } else if (result.hasErrors() || (!userDTO.getPassword().equals(userDTO.getRePassword()))) {
            result.rejectValue("rePassword", null, "Hasła się różnią");
            return "user_admin/register";
        } else {
            userService.register(userDTO);
            return "user_admin/login";
        }

    }
}
