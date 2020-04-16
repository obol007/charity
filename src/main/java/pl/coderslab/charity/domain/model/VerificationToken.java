package pl.coderslab.charity.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity @Getter @Setter  @EqualsAndHashCode(callSuper = true)
@Table(name = "tokens")
public class VerificationToken extends Base {

        private static final int EXPIRATION = 60*24;

        private String token;

        @OneToOne
        private User user;

        private Boolean active;

        private LocalDateTime expiryDate = calculateExpiryDate();

        private LocalDateTime calculateExpiryDate() {
           return LocalDateTime.now().plusMinutes(EXPIRATION);
         }

}
