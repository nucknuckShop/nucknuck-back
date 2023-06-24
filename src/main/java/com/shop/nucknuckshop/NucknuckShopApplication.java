package com.shop.nucknuckshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class NucknuckShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(NucknuckShopApplication.class, args);
    }

}
