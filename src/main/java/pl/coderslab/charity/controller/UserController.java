package pl.coderslab.charity.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.mail.EmailServiceImpl;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    UserService userService;
    PasswordEncoder passwordEncoder;
    DonationService donationService;
    EmailServiceImpl emailService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder,
                          DonationService donationService, EmailServiceImpl emailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.donationService = donationService;
        this.emailService = emailService;
    }

    @ModelAttribute("loggedUser")
    public UserDTO loggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserDTOByEmail(email);
    }

    @GetMapping
    public String userPage() {
        return "user/user";
    }

    @GetMapping("/detailsUpdated")
    public String detailsUpdated(Model model) {
        model.addAttribute("detailsUpdate", true);
        return "user/user";
    }

    @GetMapping("/passwordUpdated")
    public String passwordUpdated(Model model) {
        model.addAttribute("passwordUpdate", true);
        return "user/user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        EditUserDTO editUserDTO = userService.findEditUserDTOById(id);
        model.addAttribute("userDTO", editUserDTO);
        return "user/userEdit";
    }

    @PostMapping("/edit")
    public String editingUser(@Valid @ModelAttribute("userDTO") EditUserDTO editUserDTO, BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            return "user/userEdit";
        } else {
            userService.updateUser(editUserDTO);

            return "redirect:/user/detailsUpdated";
        }
    }

    @GetMapping("/password/{id}")
    public String changeUserPassword(@PathVariable Long id, Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserDTOByEmail(email);
        if (!userDTO.getId().equals(id)) {
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
            result.rejectValue("oldPassword", null, "Niepoprawnie wpisane stare hasło");
            return "user/changeUserPassword";

        } else if (result.hasErrors() && (passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword()))) {
            return "user/changeUserPassword";

        } else if (passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword()) &&
                !(editUserDTO.getNewPassword().equals(editUserDTO.getReNewPassword()))) {
            result.rejectValue("reNewPassword", null, "Nie wpisałeś tego samego hasła");
            return "user/changeUserPassword";
        } else {
            userService.updatePassword(editUserDTO);
            return "redirect:/user/passwordUpdated";
        }
    }

    @GetMapping("/donations/{id}")
    public String userDonations(@PathVariable Long id, Model model){
        if(!userService.checkAuthority(id)){
            return "user_admin/denied";
        }
        List<DonationDTO> donationList = donationService.findUserDonations(id);
        model.addAttribute("donations",donationList);
        return "user/userDonations";
    }

    @GetMapping("/donations/collect/{id}")
    public String collectDonation(@PathVariable Long id){

        Long userId = userService.collectDonationById(id);

        return "redirect:/user/donations/"+userId;
    }

    @GetMapping("/sendEmail")
    public String sendEmail(){
        emailService.sendSimpleMessage("p.obolewicz@gmail.com","hello","this is a test message");
        return "redirect:/user";
    }

}
