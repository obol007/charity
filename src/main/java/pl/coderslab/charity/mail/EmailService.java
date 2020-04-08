package pl.coderslab.charity.mail;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
