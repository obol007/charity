package pl.coderslab.charity.service.setup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.UserRepository;

@Service
@Profile("heroku")
public class SetupDataService implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SetupDataService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setRegistered(true);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode("Admin123$"));
        user.setBlocked(false);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setEmail("jan@kowalski");
        user.setRole("ROLE_ADMIN");

        User user2 = new User();
        user2.setRegistered(true);
        user2.setActive(true);
        user2.setPassword(passwordEncoder.encode("User123$"));
        user2.setBlocked(false);
        user2.setFirstName("Adam");
        user2.setLastName("Nowak");
        user2.setEmail("adam@nowak");
        user2.setRole("ROLE_USER");

        userRepository.save(user);
        userRepository.save(user2);

    }
}
