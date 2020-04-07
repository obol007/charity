package pl.coderslab.charity.mail;

import lombok.Data;

@Data
public class SimpleMailMessage {

    private String to;
    private String subject;
    private String text;


}
