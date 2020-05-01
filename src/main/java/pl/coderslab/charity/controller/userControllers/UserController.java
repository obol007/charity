package pl.coderslab.charity.controller.userControllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.DTO.EditNamesDTO;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.mail.EmailServiceImpl;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.PhotoService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    UserService userService;
    DonationService donationService;
    EmailServiceImpl emailService;
    InstitutionService institutionService;
    PhotoService photoService;

    public UserController(UserService userService,
                          DonationService donationService,
                          EmailServiceImpl emailService,
                          InstitutionService institutionService,
                          PhotoService photoService) {
        this.userService = userService;
        this.donationService = donationService;
        this.emailService = emailService;
        this.institutionService = institutionService;
        this.photoService = photoService;
    }

//    @ModelAttribute("loggedUser")
//    public UserDTO loggedUser() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        return userService.findUserDTOByEmail(email);
//    }

    @ModelAttribute("loggedUser")
    public UserDTO loggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Boolean hasPhoto = photoService.hasPhoto(email);
        UserDTO userDTO = userService.findUserDTOByEmail(email);
        if(hasPhoto){
            userDTO.setHasImage(true);
        }
        return userDTO;
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


    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        if(!userService.checkAuthority(id)){
            return "user_admin/denied";
        }

        EditNamesDTO user = userService.findUserToEditById(id);
        model.addAttribute("userDTO", user);
        return "user/userEdit";
    }

    @PostMapping("/edit")
    public String editingUser(@Valid @ModelAttribute("userDTO") EditNamesDTO user, BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            return "user/userEdit";
        } else {
            userService.updateUser(user);

            return "redirect:/user/detailsUpdated";
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
}
