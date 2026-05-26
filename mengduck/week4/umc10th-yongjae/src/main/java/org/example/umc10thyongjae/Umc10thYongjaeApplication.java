package org.example.umc10thyongjae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Umc10thYongjaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc10thYongjaeApplication.class, args);
    }

}
