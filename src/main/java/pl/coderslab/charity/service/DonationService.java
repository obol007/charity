package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
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

    DonationRepository donationRepository;
    UserRepository userRepository;



    public DonationService(DonationRepository donationRepository,
                           UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }


    public void add(DonationDTO donationDTO) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username);
        log.warn("Dodatcja: "+donationDTO);
        Donation donation = new Donation();
        donation.setCategories(donationDTO.getCategories());
        donation.setInstitution(donationDTO.getInstitution());
        donation.setCity(donationDTO.getCity());
        donation.setPickUpComment(donationDTO.getPickUpComment());
        donation.setPickUpDate(donationDTO.getPickUpDate());
        donation.setPickUpTime(donationDTO.getPickUpTime());
        donation.setStreet(donationDTO.getStreet());
        donation.setZipCode(donationDTO.getZipCode());
        donation.setQuantity(donationDTO.getQuantity());
        donation.setUser(user);

        donationRepository.save(donation);
    }

    public List<Donation> findUserDonations(Long id) {
        return donationRepository.findAllById(id);
    }

    public List<Donation> findAllFromActive() {
        return donationRepository.findAllFromActive();
    }
}
