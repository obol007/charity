package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.DTO.ResetPasswordDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.model.VerificationToken;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.TokenRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final TokenRepository tokenRepository;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       DonationRepository donationRepository,
                       TokenRepository tokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.tokenRepository = tokenRepository;
    }

    public VerificationToken register(UserDTO userDTO) {

        User user = new User();
//        user.setActive(true);
        user.setRole("ROLE_USER");
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(UUID.randomUUID().toString());
        tokenRepository.save(verificationToken);

        return verificationToken;
    }


    public void addOrUpdate(UserDTO userDTO) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDTO, User.class);
        user.setRole("ROLE_ADMIN");
        user.setActive(true);
        user.setBlocked(false);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }



    public UserDTO findUserDTOById(Long id) {
        Optional<User> user = userRepository.findById(id);
        UserDTO userDTO = new UserDTO();
        if (user.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            userDTO = mapper.map(user.get(), UserDTO.class);
            userDTO.setOldPassword(userDTO.getPassword());
//            userDTO.setPassword(null);
            log.warn("USER BEFORE EDIT: " + userDTO);
        }
        return userDTO;

    }

//    public void updateAdmin(AdminDTO adminDTO) {
//        Optional<User> user = userRepository.findById(adminDTO.getId());
//
//        if (user.isPresent()) {
//            User user1 = user.get();
//            user1.setFirstName(adminDTO.getFirstName());
//            user1.setLastName(adminDTO.getLastName());
//            userRepository.save(user1);
//        }
//
//    }

    public UserDTO findUserDTOByEmail(String email) {
        User user = userRepository.findByEmail(email);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserDTO.class);
    }

    public void updateUser(EditUserDTO editUserDTO) {
        Optional<User> optionalUser = userRepository.findById(editUserDTO.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLastName(editUserDTO.getLastName());
            user.setFirstName(editUserDTO.getFirstName());
            userRepository.save(user);
        }
    }

    public EditUserDTO findEditUserDTOById(Long id) {
        Optional<User> user = userRepository.findById(id);
        EditUserDTO editUserDTO = new EditUserDTO();
        if (user.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            editUserDTO = mapper.map(user.get(), EditUserDTO.class);
        }
        return editUserDTO;

    }

    public void updatePassword(EditUserDTO editUserDTO) {
        Optional<User> optionalUser = userRepository.findById(editUserDTO.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.warn("ORIGINAL USER: " + user);
            log.warn("USER OLD PASS: " + editUserDTO.getOldPassword() + " USER NEW PASS: " + editUserDTO.getNewPassword());
            user.setPassword(passwordEncoder.encode(editUserDTO.getNewPassword()));
            userRepository.save(user);
        }
    }

    public String findRoleById(Long id) {
        return userRepository.findRoleById(id);
    }

    public Boolean checkAuthority(Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        return user.getId().equals(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> allDTOUsers() {
        List<User> users = userRepository.allUsers();
        List<UserDTO> usersDTO = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (User u : users) {
            usersDTO.add(mapper.map(u, UserDTO.class));
        }
        return usersDTO;
    }

    public void changeActive(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setBlocked(!user.getBlocked());
        userRepository.save(user);
    }

    public void resetUserPassword(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode("User1234"));
            userRepository.save(user);
        }
    }

    public List<User> allAdmins() {
        return userRepository.allAdmins();
    }

    public Long collectDonationById(Long id) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            if (donation.getCollected()) {
                donation.setCollected(false);
                donation.setCollectedDate(null);
                donationRepository.save(donation);
            } else {
                donation.setCollected(true);
                donation.setCollectedDate(LocalDateTime.now());
                donationRepository.save(donation);
            }
            return donation.getUser().getId();
        }
        return null;
    }

    public void activate(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        user.setActive(true);
        user.setRegistered(true);
        userRepository.save(user);
        tokenRepository.delete(verificationToken);
    }

    public ResetPasswordDTO findUserToResetPassword(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        if(userOptional.isPresent()){
            User user = userOptional.get();
            ModelMapper mapper = new ModelMapper();
            return mapper.map(user, ResetPasswordDTO.class);
         }
        return new ResetPasswordDTO();
    }

    public String generateTokenByEmail(String email) {
        User user = userRepository.findByEmail(email);
        user.setActive(false);
        userRepository.save(user);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setUser(user);
        verificationToken.setActive(true);
        tokenRepository.save(verificationToken);
        return verificationToken.getToken();
    }

    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        User user = userRepository.findByEmail(resetPasswordDTO.getEmail());
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        VerificationToken verificationToken = tokenRepository.findByUserId(user.getId());
        tokenRepository.delete(verificationToken);
    }

    public String generateNewTokenByEmail(String email) {
        User user = userRepository.findByEmail(email);
        VerificationToken newToken = tokenRepository.findByUserId(user.getId());
        newToken.setToken(UUID.randomUUID().toString());
        newToken.setExpiryDate(LocalDateTime.now().plusMinutes(2));
        newToken.setActive(true);
        tokenRepository.save(newToken);
        return newToken.getToken();

    }
}
