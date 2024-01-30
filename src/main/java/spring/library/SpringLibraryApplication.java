package spring.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class SpringLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLibraryApplication.class, args);
    }

}
