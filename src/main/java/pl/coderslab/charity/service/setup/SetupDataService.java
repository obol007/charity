package pl.coderslab.charity.service.setup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.domain.model.Category;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.CategoryRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

@Service
@Profile("heroku")
public class SetupDataService implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;

    public SetupDataService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            InstitutionRepository institutionRepository,
                            CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
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
        user.setEmail("admin@admin");
        user.setRole("ROLE_ADMIN");

        User user2 = new User();
        user2.setRegistered(true);
        user2.setActive(true);
        user2.setPassword(passwordEncoder.encode("User123$"));
        user2.setBlocked(false);
        user2.setFirstName("Adam");
        user2.setLastName("Nowak");
        user2.setEmail("user@user");
        user2.setRole("ROLE_USER");

        userRepository.save(user);
        userRepository.save(user2);

        Category category = new Category();
        category.setName("Buty");
        categoryRepository.save(category);
        Category category1 = new Category();
        category1.setName("Ubrania");
        categoryRepository.save(category1);
        Category category2 = new Category();
        category2.setName("Zabawki");
        categoryRepository.save(category2);
        Category category3 = new Category();
        category3.setName("Książki");
        categoryRepository.save(category3);

        Institution institution = new Institution();
        institution.setName("Dla dzieci");
        institution.setDescription("Pomoc dzieciom z ubogich rodzin");
        institutionRepository.save(institution);
        Institution institution1 = new Institution();
        institution1.setName("Bez domu");
        institution1.setDescription("Pomoc bezdomnym");
        institutionRepository.save(institution1);
        Institution institution2 = new Institution();
        institution2.setName("Na wsi");
        institution2.setDescription("Pomoc dla mieszkańców wsi");
        institutionRepository.save(institution2);
        Institution institution3 = new Institution();
        institution3.setName("Niepełnosprawni");
        institution3.setDescription("Pomoc ludziom niepełnosprawnym");
        institutionRepository.save(institution3);

    }
}
