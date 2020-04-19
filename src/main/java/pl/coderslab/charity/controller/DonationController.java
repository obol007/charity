package pl.coderslab.charity.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.Category;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/donation")
@Slf4j
public class DonationController {

    DonationService donationService;
    CategoryService categoryService;
    InstitutionService institutionService;
    UserService userService;

    public DonationController(DonationService donationService,
                              CategoryService categoryService,
                              InstitutionService institutionService,
                              UserService userService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.userService = userService;
    }


    @ModelAttribute("donation")
    public DonationDTO donationForm() {
        return new DonationDTO();
    }

    @ModelAttribute("institutions")
    public List<Institution> addInstitutions() {
        return institutionService.findAllByActive();
    }

    @ModelAttribute("allCategories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("loggedUser")
    public UserDTO loggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserDTOByEmail(email);
    }


    @GetMapping
    public String addDonation() {
        return "user/form";
    }


    @PostMapping
    public String addingDonation(@Valid @ModelAttribute("donation") DonationDTO donationDTO,
                                 BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errors", true);
            return "user/form";
        }
        donationService.add(donationDTO);
        return "user/form-confirmation";
    }

}
