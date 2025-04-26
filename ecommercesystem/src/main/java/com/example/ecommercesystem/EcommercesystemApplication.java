package com.example.ecommercesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class EcommercesystemApplication {

    private static final Logger logger = LoggerFactory.getLogger(EcommercesystemApplication.class);

    public static void main(String[] args) {
        logger.info("Server wurde erfolgreich gestartet.");
        SpringApplication.run(EcommercesystemApplication.class, args);
    }
}

