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
import pl.coderslab.charity.domain.repository.CategoryRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/donation")
@Slf4j
public class DonationController {

    DonationService donationService;
    CategoryRepository categoryRepository;
    InstitutionService institutionService;

    public DonationController(DonationService donationService,
                              CategoryRepository categoryRepository,
                              InstitutionService institutionService,
                              UserRepository userRepository) {
        this.donationService = donationService;
        this.categoryRepository = categoryRepository;
        this.institutionService = institutionService;
    }

    @ModelAttribute("donation")
    public DonationDTO donationForm(){
        return new DonationDTO();}

    @ModelAttribute("institutions")
    public List<Institution> addInstitutions(){
        return institutionService.findAllByActive(true);}

 @ModelAttribute("categories")
    public List<Category> addCategories(){
        return categoryRepository.findAll();}

    @GetMapping
    public String addDonation(){
        return "user/form";}


    @GetMapping("/test")
    public String testAddDonation(){
        return "user/addDonation";
    }


    @PostMapping
    public String addingDonation(@Valid @ModelAttribute("donation") DonationDTO donationDTO,
                                 BindingResult result, Model model){
        if(result.hasErrors()){
                     model.addAttribute("errors",true);
                     return "user/form";
        }
        donationService.add(donationDTO);
        return "user/form-confirmation";
    }

}
