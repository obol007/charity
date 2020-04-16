package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.domain.model.Category;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.CategoryRepository;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DonationService {

    private DonationRepository donationRepository;
    private UserRepository userRepository;

    public DonationService(DonationRepository donationRepository,
                           UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }


    public void add(DonationDTO donationDTO) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username);
        log.warn("Dotacja: "+donationDTO);
        ModelMapper mapper = new ModelMapper();
        Donation donation = mapper.map(donationDTO, Donation.class);
        donation.setUser(user);
        donationRepository.save(donation);
    }

    public List<DonationDTO> findUserDonations(Long id) {
        List<Donation> donations = donationRepository.findAllById(id);
        List<DonationDTO> donationDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for(Donation d: donations){
            donationDTOS.add(mapper.map(d,DonationDTO.class));
        }
        return donationDTOS;
    }

    public List<Donation> findAllFromActive() {
        return donationRepository.findAllFromActive();
    }

    public List<Object[]> findAllWithNumbers() {
        return donationRepository.findAllWithNumbers();
    }

    public Integer TotalBags() {
        return donationRepository.TotalBags();
    }

    public Integer TotalDonations() {
        return donationRepository.TotalDonations();
    }
}
