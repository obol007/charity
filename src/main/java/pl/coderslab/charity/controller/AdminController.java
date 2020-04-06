package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.DTO.AdminDTO;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validation.validator.AdminValidator;

import javax.validation.Valid;
import java.util.Date;
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

    public AdminController(UserRepository userRepository,
                           DonationRepository donationRepository,
                           InstitutionRepository institutionRepository,
                           InstitutionService institutionService,
                           UserService userService) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @ModelAttribute("institutions")
    public List<Institution> allInstitutions() {
        return institutionRepository.findAllOderByIdDesc();
    }

    @ModelAttribute("admins")
    public List<User> allAdmins() {
        return userRepository.allAdmins();
    }

    @GetMapping
    public String adminPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("loggedUser", userRepository.findByEmail(username));
        return "admin";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userRepository.allUsers();

        model.addAttribute("users", users);
        model.addAttribute("showUsers", true);
        return "admin";
    }

    @GetMapping("/admins")
    public String admins(Model model) {
        model.addAttribute("showAdmins", true);
        return "administrators";
    }

    @GetMapping("/institution")
    public String institutions(Model model) {
        model.addAttribute("showInstitutions", true);
        return "admin";
    }

    @GetMapping("/institution/active/{id}")
    public String institutionsActivation(@PathVariable Long id, Model model) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();
            //NICE!
            institution.setActive(!institution.getActive());
//            if(institution.getActive()) {
//                institution.setActive(false);
//            }else{
//                institution.setActive(true);
//            }
            institutionRepository.save(institution);
        }
        model.addAttribute("showInstitutions", true);
        return "admin";
    }

    @GetMapping("/donations")
    public String donations(Model model) {
        List<Donation> donations = donationRepository.findAll();

        model.addAttribute("donations", donations);
        model.addAttribute("showDonations", true);
        return "admin";
    }

    @GetMapping("/institution/add")
    public String addInstitution(Model model) {
        model.addAttribute("showInstitutions", true);
        InstitutionDTO institutionDTO = new InstitutionDTO();
        model.addAttribute("newInstitution", institutionDTO);
        return "admin";
    }

    @PostMapping("/institution/add")
    public String addingInstitution(@Valid @ModelAttribute("newInstitution") InstitutionDTO institutionDTO,
                                    BindingResult result, Model model) {
        model.addAttribute("showInstitutions", true);

        if (result.hasErrors()) {
            return "admin";
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
        return "admin";
    }

    @GetMapping("/institution/delete/{id}")
    public String deleteInstitution(@PathVariable Long id, Model model) {
        InstitutionDTO institutionDTO = institutionService.findInstitution(id);
        model.addAttribute("institutionToDelete", institutionDTO);
        if (institutionDTO.getActive()) {
            model.addAttribute("showInstitutions", true);
            model.addAttribute("isActive", true);
            return "admin";
        } else {
            model.addAttribute("showInstitutions", true);
            model.addAttribute("isActive", false);
            return "admin";
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
        return "addAdmin";
    }


    @PostMapping("/admins/add")
    public String addingAdmin(@Validated({AdminValidator.class}) UserDTO userDTO,
                                    BindingResult result, Model model) {
        if (result.hasErrors() && (userDTO.getPassword().equals(userDTO.getRePassword()))) {
            model.addAttribute("addAdmin", true);
            return "addAdmin";
        } else if (result.hasErrors() || (!userDTO.getPassword().equals(userDTO.getRePassword()))) {
            result.rejectValue("rePassword", null, "Hasła się różnią");
            model.addAttribute("addAdmin", true);
            return "addAdmin";
        } else {
            userService.addOrUpdate(userDTO);
            return "redirect:/admin/admins";
        }
    }

    @GetMapping("/admins/edit/{id}")
    public String editAdmin(@PathVariable Long id, Model model) {
        AdminDTO adminDTO = userService.findAdminDTOByID(id);
        model.addAttribute("adminDTO", adminDTO);
        return "editAdmin";
    }



    @PostMapping("/admins/edit")
    public String editingAdmin(@Valid @ModelAttribute("adminDTO") AdminDTO adminDTO,
                               BindingResult result) {
        log.warn("CZY SA BLEDY?");
        if (result.hasErrors()) {
            log.warn("SA BŁĘÐY");
            return "editAdmin";
        } else {
            log.warn("NIE MA BŁĘDÓW");
            userService.updateAdmin(adminDTO);
            return "redirect:/admin/admins";
        }
    }

//        if(result.hasErrors() && userDTO.getOldPassword().equals(userDTO.getTryOldPassword())
//        && userDTO.getPassword().equals(userDTO.getRePassword())){
//            return "editAdmin";
//        } else if(!userDTO.getOldPassword().equals(userDTO.getTryOldPassword())) {
//            result.rejectValue("tryOldPassword", null, "Niepoprawne hasło");
//            return "editAdmin";
//        }else if(userDTO.getOldPassword().equals(userDTO.getTryOldPassword()) &&
//                !userDTO.getPassword().equals(userDTO.getRePassword())){
//            result.rejectValue("rePassword",null,"Hasła się nie zgadzają");
//            return "editAdmin";
//        }else{
//            userService.editAdmin(userDTO);
//            return "redirect:/admin/admins";
//        }







}
