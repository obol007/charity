package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validation.validator.AdminValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    UserService userService;

    public AdminController(UserService userService
    ) {
        this.userService = userService;
    }

    @ModelAttribute("admins")
    public List<UserDTO> allAdmins() {
        return userService.allDTOUsersByRole("ROLE_ADMIN");
    }

    @ModelAttribute("loggedUser")
    public UserDTO loggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserDTOByEmail(email);
    }

    @GetMapping
    public String adminPage() {
        return "admin/admin";
    }


    @GetMapping("/admins")
    public String admins(Model model) {
        model.addAttribute("showAdmins", true);
        return "admin/administrators";
    }


    @GetMapping("/admins/add")
    public String addAdmin(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "admin/addAdmin";
    }

    @PostMapping("/admins/add")
    public String addingAdmin(@Validated({AdminValidator.class}) UserDTO userDTO,
                              BindingResult result, Model model) {
        if (result.hasErrors() && (userDTO.getPassword().equals(userDTO.getRePassword()))) {
            model.addAttribute("addAdmin", true);
            return "admin/addAdmin";
        } else if (result.hasErrors() || (!userDTO.getPassword().equals(userDTO.getRePassword()))) {
            result.rejectValue("rePassword", "password.notMatches");
            model.addAttribute("addAdmin", true);
            return "admin/addAdmin";
        } else {
            userService.addOrUpdate(userDTO);
            return "redirect:/admin/admins";
        }
    }


    @GetMapping("/admins/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        EditUserDTO editUserDTO = userService.findEditUserDTOById(id);
        model.addAttribute("adminDTO", editUserDTO);
        return "admin/editAdmin";
    }

    @PostMapping("/admins/edit")
    public String editingUser(@Valid @ModelAttribute("adminDTO") EditUserDTO editUserDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "user/userEdit";
        } else {
            userService.updateUser(editUserDTO);
            return "redirect:/admin/admins";
        }
    }

    @GetMapping("/admins/delete/{id}")
    public String deleteAdmin(@PathVariable Long id, Model model) {
        if (userService.checkAuthority(id)) {
            return "admin/cantDelete";
        }
        UserDTO userDTO = userService.findUserDTOById(id);
        model.addAttribute("adminToDelete", userDTO);
        return "admin/toDelete";
    }

    @PostMapping("/admins/delete")
    public String deletingAdmin(UserDTO userDTO) {
        userService.deleteUserById(userDTO.getId());
        return "redirect:/admin/admins";
    }




}
