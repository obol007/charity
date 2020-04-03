package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    UserRepository userRepository;
    DonationRepository donationRepository;
    InstitutionRepository institutionRepository;

    public AdminController(UserRepository userRepository,
                           DonationRepository donationRepository,
                           InstitutionRepository institutionRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public String adminPage(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("loggedUser",userRepository.findByEmail(username));
        return "admin";
    }
    @GetMapping("/users")
    public String users(Model model){
        List<User> users = userRepository.allUsers();

        model.addAttribute("users",users);
        model.addAttribute("showUsers",true);
        return "admin";
    }
    @GetMapping("/admins")
    public String admins(Model model){
        List<User> users = userRepository.allAdmins();

        model.addAttribute("admins",users);
        model.addAttribute("showAdmins",true);
        return "admin";
    }

    @GetMapping("/institutions")
    public String institutions(Model model){
        List<Institution> institutions = institutionRepository.findAll();

        model.addAttribute("institutions",institutions);
        model.addAttribute("showInstitutions",true);
        return "admin";
    }
    @GetMapping("/institutions/active/{id}")
    public String institutionsActivation(@PathVariable Long id, Model model){
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if(optionalInstitution.isPresent()){
            Institution institution = optionalInstitution.get();
            if(institution.getActive()==true) {
                institution.setActive(false);
            }else{
                institution.setActive(true);
            }
            institutionRepository.save(institution);
        }
        model.addAttribute("institutions",institutionRepository.findAll());
        model.addAttribute("showInstitutions",true);
        return "admin";
    }

    @GetMapping("/donations")
    public String donations(Model model){
        List<Donation> donations = donationRepository.findAll();

        model.addAttribute("donations",donations);
        model.addAttribute("showDonations",true);
        return "admin";
    }

}
