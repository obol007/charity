package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}