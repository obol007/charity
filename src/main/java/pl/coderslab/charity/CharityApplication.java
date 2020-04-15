package pl.coderslab.charity;

import org.hibernate.annotations.SQLInsert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@SQLInsert(sql = "INSERT INTO users values (98,true,false,'obol@wp.pl','Piotr','Obol','Admin1234','ROLE_ADMIN')")
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

}
