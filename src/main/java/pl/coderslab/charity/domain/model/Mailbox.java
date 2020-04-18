package pl.coderslab.charity.domain.model;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Mailbox extends Base {

    private String title;
    private String text;
    private String sender = "javacoderslab@gmail.com";
    private String recipient;
    private Boolean opened = false;

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")
    private LocalDateTime created;
}
