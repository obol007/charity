package pl.coderslab.charity.controller.commonControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {


    InstitutionService institutionService;
    DonationService donationService;
    UserService userService;

    public HomeController(InstitutionService institutionService,
                          DonationService donationService,
                          UserService userService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @GetMapping
    public String homeAction(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        Integer bags = ((donationService.TotalBags() == null) ? 0 : donationService.TotalBags());
        model.addAttribute("totalBags", bags);
        model.addAttribute("totalDonations", donationService.TotalDonations());
        model.addAttribute("institutions", institutionService.findAllByActive());

        if (!role.equals("[ROLE_ANONYMOUS]")) {
            model.addAttribute("loggedUser", userService.findUserDTOByEmail(username));
        }
        return "user_admin/index";
    }


}
