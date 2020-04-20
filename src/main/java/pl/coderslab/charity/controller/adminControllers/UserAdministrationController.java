package pl.coderslab.charity.controller.adminControllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.EditNamesDTO;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/users")
@Slf4j
public class UserAdministrationController {

    UserService userService;

    public UserAdministrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("users")
    public List<UserDTO> users() {
        return userService.allDTOUsersByRole("ROLE_USER");
    }

    @GetMapping()
    public String allUsers() {
        return "admin/usersDetails";
    }

    @GetMapping("/active/{id}")
    public String institutionsActivation(@PathVariable Long id) {
        userService.changeActive(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        EditNamesDTO user = userService.findUserToEditById(id);
        model.addAttribute("userDTO", user);
        return "admin/editUser";
    }

    @PostMapping("/edit")
    public String editingUser(@Valid @ModelAttribute("userDTO") EditNamesDTO user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/editUser";
        } else {
            userService.updateUser(user);
            return "redirect:/admin/users";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id, Model model){

        EditUserDTO editUserDTO = userService.findEditUserDTOById(id);
        model.addAttribute("userToDelete",editUserDTO);
        return "admin/userToDelete";
    }
    @PostMapping("/delete")
    public String deletingAdmin(EditUserDTO editUserDTO){
        userService.deleteUserById(editUserDTO.getId());
        return "redirect:/admin/users";
    }
    @GetMapping("/password/{id}")
    public String resetPassword(@PathVariable Long id, Model model){
        userService.resetUserPassword(id);
        model.addAttribute("passReset",true);
        return "admin/usersDetails";
    }

    @PostMapping("/password")
    public String resetPasswordPost(@ModelAttribute("idToReset") Long id, Model model){
        log.warn("Przesłane id: "+id);
        userService.resetUserPassword(id);
        model.addAttribute("passReset",true);
        return "admin/usersDetails";
    }



}
