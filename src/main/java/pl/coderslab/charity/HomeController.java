package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;


@Controller
public class HomeController {

    InstitutionRepository institutionRepository;
    DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository,
                          DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        Integer bags;
        if(donationRepository.TotalBags()==null){
            bags = 0;
        }
        else{
            bags=donationRepository.TotalBags();
        }
        model.addAttribute("totalBags", bags);
        model.addAttribute("totalDonations", donationRepository.TotalDonations());

        model.addAttribute("institutions",institutionRepository.findAll());
        return "index";
    }
}
