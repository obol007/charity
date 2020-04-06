package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.AdminDTO;
import pl.coderslab.charity.DTO.EditUserDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService {


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


    public void addOrUpdate(UserDTO userDTO) {
        log.warn("USER_DTO: " + userDTO);
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDTO, User.class);
        user.setRole("ROLE_ADMIN");
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        log.warn("USER: " + user);
        userRepository.save(user);
    }

    public void editAdmin(UserDTO userDTO) {
        log.warn("USER_DTO: " + userDTO);
        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.warn("USER BEFORE UPDATE: " + user);
            user.setId(userDTO.getId());
            user.setPassword(userDTO.getPassword());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            log.warn("USER AFTER UPDATE: " + user);
//            userRepository.UpdateAdminById(user.getId());
            userRepository.save(user);
        }
    }

    public AdminDTO findAdminDTOByID(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        AdminDTO adminDTO = new AdminDTO();
        if (userOptional.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            adminDTO = mapper.map(userOptional.get(), AdminDTO.class);
        }
        return adminDTO;
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

    public void updateAdmin(AdminDTO adminDTO) {
        Optional<User> user = userRepository.findById(adminDTO.getId());

        if (user.isPresent()) {
            User user1 = user.get();
            user1.setFirstName(adminDTO.getFirstName());
            user1.setLastName(adminDTO.getLastName());
            userRepository.save(user1);
        }

    }

    public UserDTO findUserDTOByEmail(String email) {
        User user = userRepository.findByEmail(email);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserDTO.class);
    }

    public void updateUser(EditUserDTO editUserDTO) {
        Optional<User> optionalUser = userRepository.findById(editUserDTO.getId());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setLastName(editUserDTO.getLastName());
            user.setFirstName(editUserDTO.getFirstName());
            user.setPassword(passwordEncoder.encode(editUserDTO.getNewPassword()));
            userRepository.save(user);
        }
    }

    public EditUserDTO findEditUserDTOById(Long id) {
        Optional<User> user = userRepository.findById(id);
        EditUserDTO editUserDTO = new EditUserDTO();
        if (user.isPresent()) {
            ModelMapper mapper = new ModelMapper();
            editUserDTO = mapper.map(user.get(), EditUserDTO.class);
            log.warn("MAPPED EDIT USER DTO: " + editUserDTO);
        }
        return editUserDTO;

    }

}
