package com.visme.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.visme.app.data.repositories"})
@EntityScan("com.visme.app.data.domain")
@SpringBootApplication
public class TestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

}

