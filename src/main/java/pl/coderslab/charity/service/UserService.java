package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.UserRepository;

@Service
@Transactional
@Slf4j
public class UserService {

//TODO:password encoder
        private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;

        this.userRepository = userRepository;
    }

    public void register(UserDTO userDTO) {

        User user = new User();

        user.setActive(true);
        user.setRole("ROLE_USER");
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);





    }
}
