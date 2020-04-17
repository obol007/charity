package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.DTO.ExtraData;
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

    @Query("select d from Donation d where d.user.id = ?1 order by d.collected, d.createdOn desc")
    List<Donation> findAllById(Long id);

    @Query("select d from Donation d join d.user u where u.active=true")
    List<Donation> findAllFromActive();

    @Query("select d.user, sum(d.quantity), count(d.user.id) from Donation d join d.user u where u.active=true group by u.id")
    List<Object[]> findAllWithNumbers();

    @Query("select new pl.coderslab.charity.DTO.ExtraData(d.user, sum(d.quantity), count(d.user.id)) from Donation d join d.user u where u.active=true group by u.id")
    List<ExtraData> findExtraData();



//    @Query(value = "select pl.coderslab.charity.DTO.ExtraData(users.id, email, first_name, last_name, count(users.id),sum(d.quantity)) from users join donation d on users.id = d.user_id group by users.id;", nativeQuery = true)
//    List<ExtraData> findObjectsWithExtraData();
}
