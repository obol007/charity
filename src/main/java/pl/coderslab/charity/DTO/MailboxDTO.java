package pl.coderslab.charity.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class MailboxDTO {

    private Long id;
    private String title;
    private String text;
    private String recipient;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime created;
    private Boolean opened;
    private String sender;


}
