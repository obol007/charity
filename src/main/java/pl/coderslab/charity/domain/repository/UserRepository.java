package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.domain.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("select u from User u where u.role= ?1")
    List<User> allUsersByRole(String role);

    @Query("select u from User u where u.role='ROLE_USER' ")
    List<User> allUsers();
    @Query("select u from User u where u.role='ROLE_ADMIN' ")
    List<User> allAdmins();





}
