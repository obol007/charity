package pl.coderslab.charity.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;


@Entity @Getter @Setter  @EqualsAndHashCode(callSuper = true)
public class VerificationToken extends Base {

        private static final int EXPIRATION = 60 * 24;


        private String token;

        @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//        @JoinColumn(nullable = false, name = "id")
        private User user;

        private Date expiryDate;

        private Date calculateExpiryDate(int expiryTimeInMinutes) {
            Calendar cal = Calendar.getInstance();
//            cal.setTime(new Timestamp(cal.getTime().getTime()));
            cal.add(Calendar.MINUTE, expiryTimeInMinutes);
            return new Date(cal.getTime().getTime());
        }

}
