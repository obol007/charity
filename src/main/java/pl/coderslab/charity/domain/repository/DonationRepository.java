package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.domain.model.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(d.quantity) from Donation d")
    Integer TotalBags();

    @Query("select count(d) from Donation d")
    Integer TotalDonations();

    @Query("select sum(d.quantity) from Donation d where d.user.email=?1")
    Integer BagsByUser(String email);

    @Query("select count(d) from Donation d where d.user.email=?1")
    Integer DonationsByUser(String email);

    @Query(value = "select pl.coderslab.charity.domain.repository.SomeStrangeDataResults(users.id, email, first_name, last_name, count(users.id)) from users join donation d on users.id = d.user_id group by users.id;", nativeQuery = true)
    List<SomeStrangeDataResults> findObjectsWithDonations();


}
