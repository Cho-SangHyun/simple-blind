package com.example.simpleblind.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.simpleblind")
public class SimpleBlindApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBlindApplication.class, args);
    }
}
