package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.ExtraData;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validation.validator.AdminValidator;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    UserRepository userRepository;
    DonationRepository donationRepository;
    InstitutionRepository institutionRepository;
    InstitutionService institutionService;
    UserService userService;
    PasswordEncoder passwordEncoder;

    public AdminController(UserRepository userRepository,
                           DonationRepository donationRepository,
                           InstitutionRepository institutionRepository,
                           InstitutionService institutionService,
                           UserService userService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
        this.institutionService = institutionService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("institutions")
    public List<Institution> allInstitutions() {
        return institutionRepository.findAllOderByIdDesc();
    }

    @ModelAttribute("admins")
    public List<User> allAdmins() {
        return userRepository.allAdmins();
    }

    @ModelAttribute("loggedUser")
    public UserDTO loggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserDTOByEmail(email);
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userRepository.allUsers();
    }
//    @ModelAttribute("usersExtra")
//    public List<ExtraData> extraData(){
//        return donationRepository.findObjectsWithExtraData();
//    }

    @GetMapping
    public String adminPage(Model model) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        model.addAttribute("loggedUser", userRepository.findByEmail(username));
        return "admin/admin";
    }

    @GetMapping("/users")
    public String users(Model model) {
        return "admin/usersDetails";
    }

    @GetMapping("/admins")
    public String admins(Model model) {
        model.addAttribute("showAdmins", true);
        return "admin/administrators";
    }

    @GetMapping("/institution")
    public String institutions(Model model) {
        model.addAttribute("showInstitutions", true);
        return "admin/admin";
    }

    @GetMapping("/institution/active/{id}")
    public String institutionsActivation(@PathVariable Long id, Model model) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();
            institution.setActive(!institution.getActive());
            institutionRepository.save(institution);
        }
        model.addAttribute("showInstitutions", true);
        return "admin/admin";
    }

    @GetMapping("/donations")
    public String donations(Model model) {
        List<Donation> donations = donationRepository.findAll();

        model.addAttribute("donations", donations);
        model.addAttribute("showDonations", true);
        return "admin/admin";
    }

    @GetMapping("/institution/add")
    public String addInstitution(Model model) {
        model.addAttribute("showInstitutions", true);
        InstitutionDTO institutionDTO = new InstitutionDTO();
        model.addAttribute("newInstitution", institutionDTO);
        return "admin/admin";
    }

    @PostMapping("/institution/add")
    public String addingInstitution(@Valid @ModelAttribute("newInstitution") InstitutionDTO institutionDTO,
                                    BindingResult result, Model model) {
        model.addAttribute("showInstitutions", true);

        if (result.hasErrors()) {
            return "admin/admin";
        }
        institutionService.addOrUpdate(institutionDTO);
        return "redirect:/admin/institution";
    }

    @GetMapping("/institution/edit/{id}")
    public String editInstitution(Model model, @PathVariable Long id) {
        InstitutionDTO institutionDTO = institutionService.findInstitution(id);
        log.warn("INSTITUTION: " + institutionDTO);

        model.addAttribute("newInstitution", institutionDTO);
        model.addAttribute("showInstitutions", true);
        return "admin/admin";
    }

    @GetMapping("/institution/delete/{id}")
    public String deleteInstitution(@PathVariable Long id, Model model) {
        InstitutionDTO institutionDTO = institutionService.findInstitution(id);
        model.addAttribute("institutionToDelete", institutionDTO);
        if (institutionDTO.getActive()) {
            model.addAttribute("showInstitutions", true);
            model.addAttribute("isActive", true);
            return "admin/admin";
        } else {
            model.addAttribute("showInstitutions", true);
            model.addAttribute("isActive", false);
            return "admin/admin";
        }
    }

    @GetMapping("/institution/delete/confirmed/{id}")
    public String deletingInstitution(@PathVariable Long id, Model model) {
        institutionService.delete(id);
        model.addAttribute("showInstitutions", true);
        return "redirect:/admin/institution";
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
            result.rejectValue("rePassword", null, "Hasła się różnią");
            model.addAttribute("addAdmin", true);
            return "admin/addAdmin";
        } else {
            userService.addOrUpdate(userDTO);
            return "redirect:/admin/admins";
        }
    }


    @GetMapping("/admins/edit/{id}")
    public String editUser(@PathVariable Long id, EditUserDTO editUserDTO, Model model) {
        editUserDTO = userService.findEditUserDTOById(id);
        model.addAttribute("adminDTO", editUserDTO);
        return "admin/editAdmin";
    }

    @PostMapping("/admins/edit")
    public String editingUser(@Valid @ModelAttribute("adminDTO") EditUserDTO editUserDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "user/userEdit";
        } else {
            log.warn("USERDTO: " + editUserDTO);
            userService.updateUser(editUserDTO);
            return "redirect:/admin/admins";
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
        return "admin/changeAdminPassword";
    }

    @PostMapping("/password")
    public String changingUserPassword(@Valid @ModelAttribute("userDTO") EditUserDTO editUserDTO, BindingResult result,
                                       Model model) {

        if (!passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword())) {
            result.rejectValue("oldPassword", null, "Niepoprawnie wpisane stare hasło");
            return "admin/changeAdminPassword";

        } else if (result.hasErrors() && (passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword()))) {
            return "admin/changeAdminPassword";

        } else if (passwordEncoder.matches(editUserDTO.getOldPassword(), editUserDTO.getPassword()) &&
                !(editUserDTO.getNewPassword().equals(editUserDTO.getReNewPassword()))) {
            result.rejectValue("reNewPassword", null, "Nie wpisałeś tego samego hasła");
            return "admin/changeAdminPassword";

        } else {
            userService.updatePassword(editUserDTO);
            return "redirect:/admin/passwordUpdated";
        }
    }

    @GetMapping("/passwordUpdated")
    public String passwordUpdated(Model model) {
        model.addAttribute("passwordUpdate", true);
        return "/admin/admin";
    }

    @GetMapping("/admins/delete/{id}")
    public String deleteAdmin(@PathVariable Long id, Model model){
        if(userService.checkAuthority(id)){
            return "admin/cantDelete";
        }

        UserDTO userDTO = userService.findUserDTOById(id);
        model.addAttribute("adminToDelete",userDTO);
        return "admin/toDelete";
    }
    @PostMapping("/admins/delete")
    public String deletingAdmin(UserDTO userDTO){
        userService.deleteAdminById(userDTO.getId());
        return "redirect:/admin/admins";
    }


}
