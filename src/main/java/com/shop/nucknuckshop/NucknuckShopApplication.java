package com.shop.nucknuckshop;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
@EnableBatchProcessing
public class NucknuckShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(NucknuckShopApplication.class, args);
    }

}
