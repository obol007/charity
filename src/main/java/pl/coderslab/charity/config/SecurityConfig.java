package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.coderslab.charity.mail.JavaMailSenderImpl;
import pl.coderslab.charity.mail.SimpleMailMessage;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("javacoderslab@gmail.com");
        mailSender.setPassword("taxcdrogbymnhhbj");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "This is the test email template for your email:\n%s\n");
        return message;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT email, password, active FROM users " +
                        "WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, role FROM users " +
                        "WHERE email = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                //konfigurujemy od szczegolu do ogolu (jak w try - catch)
                .antMatchers("/").permitAll()
                 .antMatchers("/resources/","/resources/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/lang/","/lang/**").permitAll()
                .antMatchers("/admin/","/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/","/user/**").hasAuthority("ROLE_USER")
                .antMatchers("/donation").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/password").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/superadmin").hasAuthority("ROLE_SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
//                .successForwardUrl("/")

                //po zalogowaniu przechodzi na strone na ktora chcial wejsc
                .defaultSuccessUrl("/login",true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                //formularz do zabezpieczenia przed wymuszaniem danych
                .csrf();
    }


}