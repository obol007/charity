package pl.coderslab.charity.controller.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.DTO.ExtraData;
import pl.coderslab.charity.service.DonationService;

import java.util.List;

@Controller
@RequestMapping("/admin/donations")
public class AdminDonationController {

    DonationService donationService;

    public AdminDonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping
    public String donations(Model model) {
        List<ExtraData> extraData = donationService.findExtraData();
        model.addAttribute("donations", extraData);
        return "admin/donations";
    }

    @GetMapping("/{id}")
    public String showUserDonations(@PathVariable Long id, Model model) {
        List<DonationDTO> donations = donationService.findUserDonations(id);
        model.addAttribute("donations", donations);
        return "admin/userDonations";
    }
}
