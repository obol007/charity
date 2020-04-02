package pl.coderslab.charity.controller;


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
import pl.coderslab.charity.domain.repository.CategoryRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    DonationService donationService;
    CategoryRepository categoryRepository;
    InstitutionRepository institutionRepository;

    public DonationController(DonationService donationService,
                              CategoryRepository categoryRepository,
                              InstitutionRepository institutionRepository) {
        this.donationService = donationService;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }


    @GetMapping
    public String addDonation(Model model){
        List<Category> categories = categoryRepository.findAll();
        List<Institution> institutions = institutionRepository.findAll();
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
