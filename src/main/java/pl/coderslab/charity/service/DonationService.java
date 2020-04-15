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
        ModelMapper mapper = new ModelMapper();
        Donation donation = new Donation();
        log.warn("DONATIION BEFORE MAPPING: "+donation);

        donation = mapper.map(donationDTO, Donation.class);
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
}
