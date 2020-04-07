package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.domain.model.User;

import java.util.List;
import java.util.Map;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("select sum(d.quantity) from Donation d")
    Integer TotalBags();

    @Query("select count(d) from Donation d")
    Integer TotalDonations();

    @Query("select sum(d.quantity) from Donation d where d.user.email=?1")
    Integer BagsByUser(String email);

    @Query("select count(d) from Donation d where d.user.email=?1")
    Integer DonationsByUser(String email);

    @Query(value = "select users.id, email, first_name, last_name, count(users.id) as donations, sum(d.quantity) from users join donation d on users.id = d.user_id group by users.id;",nativeQuery = true)
    List<Object> findObjectsWithDonations();

//    @Query(value = "select pl.coderslab.charity.domain.repository.ExtraData(users.id, email, first_name, last_name, count(users.id),sum(d.quantity)) from users join donation d on users.id = d.user_id group by users.id;", nativeQuery = true)
//    List<ExtraData> findObjectsWithExtraData();
}
