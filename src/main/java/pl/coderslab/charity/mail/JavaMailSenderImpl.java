package pl.coderslab.charity.mail;

import lombok.Data;

import java.util.Properties;

@Data
public class JavaMailSenderImpl {

    private String host;
    private int port;
    private String username;
    private String password;

    public Properties getJavaMailProperties() {

        return new Properties();
    }
}
