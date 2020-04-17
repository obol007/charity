package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.domain.model.VerificationToken;

public interface TokenRepository extends JpaRepository <VerificationToken, Long> {

    @Query("select v from VerificationToken v where v.token=?1")
    VerificationToken findByToken(String activationToken);

    @Query("select v from VerificationToken  v where v.user.id=?1")
    VerificationToken findByUserId(Long id);


}
