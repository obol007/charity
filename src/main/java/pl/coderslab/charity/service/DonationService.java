package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.DonationDTO;
import pl.coderslab.charity.domain.model.Category;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.domain.repository.CategoryRepository;
import pl.coderslab.charity.domain.repository.DonationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DonationService {

    DonationRepository donationRepository;
    CategoryRepository categoryRepository;



    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }


    public void add(DonationDTO donationDTO) {
        log.warn("Dodatcja: "+donationDTO);
        Donation donation = new Donation();
        log.warn("Pobrane kategorie: "+String.valueOf(donationDTO.getCategories()));
//        List<Category> categoryList = new ArrayList<>();
//        for(Category c: donationDTO.getCategories()){
//            categoryList.add(c);
//
//        }






        donation.setCategories(donationDTO.getCategories());


        donation.setInstitution(donationDTO.getInstitution());
        donation.setCity(donationDTO.getCity());
        donation.setPickUpComment(donationDTO.getPickUpComment());
        donation.setPickUpDate(donationDTO.getPickUpDate());
        donation.setPickUpTime(donationDTO.getPickUpTime());
        donation.setStreet(donationDTO.getStreet());
        donation.setZipCode(donationDTO.getZipCode());
        donation.setQuantity(donationDTO.getQuantity());

        donationRepository.save(donation);
    }
}
