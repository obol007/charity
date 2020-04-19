package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/password")
public class AdminChangePassController {

    UserService userService;
    PasswordEncoder passwordEncoder;

    public AdminChangePassController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}")
    public String changeUserPassword(@PathVariable Long id, Model model) {
        if (!userService.checkAuthority(id)) {
            return "user_admin/denied";
        }
        EditUserDTO editUserDTO = userService.findEditUserDTOById(id);
        model.addAttribute("userDTO", editUserDTO);
        return "admin/changeAdminPassword";
    }

    @PostMapping
    public String changingUserPassword(@Valid @ModelAttribute("userDTO") EditUserDTO editUserDTO,
                                       BindingResult result, Model model) {

        if (!passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword())) {
            result.rejectValue("oldPassword",  "oldPassword.notMatches");
            return "admin/changeAdminPassword";

        } else if (result.hasErrors() && (passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword()))) {
            return "admin/changeAdminPassword";

        } else if (passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword()) &&
                !(editUserDTO.getNewPassword().equals(editUserDTO.getReNewPassword()))) {
            result.rejectValue("reNewPassword", "password.notMatches");
            return "admin/changeAdminPassword";

        } else {
            userService.updatePassword(editUserDTO);
            model.addAttribute("passwordUpdate", true);
            return "admin/passwordUpdated";
        }
    }

}


