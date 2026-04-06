package com.example.simpleblind.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.simpleblind")
@EntityScan("com.example.simpleblind.domain")
@EnableJpaRepositories("com.example.simpleblind.infra")
public class SimpleBlindApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBlindApplication.class, args);
    }
}
