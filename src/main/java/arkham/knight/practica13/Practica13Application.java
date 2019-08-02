package arkham.knight.practica13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Practica13Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Practica13Application.class, args);
    }

}
