package com.example.simpleblind.application.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.example.simpleblind.domain")
@EnableJpaRepositories("com.example.simpleblind.infra")
public class JpaConfig {}
