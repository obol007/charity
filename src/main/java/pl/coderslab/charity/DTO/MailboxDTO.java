package pl.coderslab.charity.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MailboxDTO {

    private Long id;
    private String title;
    private String text;
    private String recipient;
    private LocalDateTime created;
    private Boolean read;
    private String sender;


}
