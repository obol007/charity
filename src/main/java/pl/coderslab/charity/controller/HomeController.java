package pl.coderslab.charity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.security.Principal;


@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    InstitutionRepository institutionRepository;
    DonationRepository donationRepository;
    UserRepository userRepository;

    public HomeController(InstitutionRepository institutionRepository,
                          DonationRepository donationRepository,
                          UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }


    @GetMapping
    public String homeAction(Model model, Principal principal) {
        Integer bags;
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        log.warn("USERNAME: " + username);
        log.warn("ROLA: " + role);


        if (donationRepository.TotalBags() == null) {
            bags = 0;
        } else {
            bags = donationRepository.TotalBags();
        }
        if (role.equals("[ROLE_USER]")) {


                log.warn("JESTEM TUTAJ");
            model.addAttribute("loggedUser", userRepository.findByEmail(username));
        }

        model.addAttribute("totalBags", bags);
        model.addAttribute("totalDonations", donationRepository.TotalDonations());

        model.addAttribute("institutions", institutionRepository.findAll());
        return "index";
    }
}
