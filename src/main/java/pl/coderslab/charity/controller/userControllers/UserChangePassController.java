package pl.coderslab.charity.controller.userControllers;

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
public class UserChangePassController {

    PasswordEncoder passwordEncoder;
    UserService userService;

    public UserChangePassController(PasswordEncoder passwordEncoder,
                                    UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @ModelAttribute("loggedUser")
    public UserDTO loggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserDTOByEmail(email);
    }

    @GetMapping("/password/{id}")
    public String changeUserPassword(@PathVariable Long id, Model model) {
        if(!userService.checkAuthority(id)){
            return "user_admin/denied";
        }
        EditUserDTO editUserDTO = userService.findEditUserDTOById(id);
        model.addAttribute("userDTO", editUserDTO);
        return "user/changeUserPassword";
    }

    @PostMapping("/password")
    public String changingUserPassword(@Valid @ModelAttribute("userDTO") EditUserDTO editUserDTO, BindingResult result,
                                       Model model) {

        if (!passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword())) {
            result.rejectValue("oldPassword",  "oldPassword.notMatches");
            return "user/changeUserPassword";

        } else if (result.hasErrors() && (passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword()))) {
            return "user/changeUserPassword";

        } else if (passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword()) &&
                !(editUserDTO.getNewPassword().equals(editUserDTO.getReNewPassword()))) {
            result.rejectValue("reNewPassword",  "password.notMatches");
            return "user/changeUserPassword";
        } else {
            userService.updatePassword(editUserDTO);
            return "redirect:/user/passwordUpdated";
        }
    }

    @GetMapping("/passwordUpdated")
    public String passwordUpdated(Model model) {
        model.addAttribute("passwordUpdate", true);
        return "user/user";
    }
}
