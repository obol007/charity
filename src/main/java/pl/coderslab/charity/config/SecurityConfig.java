package pl.coderslab.charity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

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