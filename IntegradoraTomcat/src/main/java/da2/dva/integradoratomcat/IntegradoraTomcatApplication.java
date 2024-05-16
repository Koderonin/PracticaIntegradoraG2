package da2.dva.integradoratomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "da2.dva.integradoratomcat.repositories.jpa")
@EnableMongoRepositories(basePackages = "da2.dva.integradoratomcat.repositories.mongo")
public class IntegradoraTomcatApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegradoraTomcatApplication.class, args);
    }

}
