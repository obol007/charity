package pl.coderslab.charity.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.domain.model.Category;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.CategoryRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;
import pl.coderslab.charity.service.DonationService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/donation")
@Slf4j
public class DonationController {

    DonationService donationService;
    CategoryRepository categoryRepository;
    InstitutionRepository institutionRepository;
    UserRepository userRepository;

    public DonationController(DonationService donationService,
                              CategoryRepository categoryRepository,
                              InstitutionRepository institutionRepository,
                              UserRepository userRepository) {
        this.donationService = donationService;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
    }



    @GetMapping
    public String addDonation(Model model, Principal principal){
        List<Category> categories = categoryRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();

        log.warn("Principal name: "+principal.getName());

        model.addAttribute("donation",new DonationDTO());
        model.addAttribute("institutions",institutions);
        model.addAttribute("categories",categories);
        return "form";
    }
    @PostMapping
    public String addingDonation(@Valid @ModelAttribute("donation") DonationDTO donationDTO,
                                 BindingResult result){
        if(result.hasErrors()){
            return "form";
        }
        donationService.add(donationDTO);
        return "form-confirmation";
    }

}
