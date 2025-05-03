package com.acme.crmsystem;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableRabbit
public class CrmsystemApplication {

	private static final Logger logger = LoggerFactory.getLogger(CrmsystemApplication.class);

	public static void main(String[] args) {
		logger.info("Server wurde erfolgreich gestartet.");
		SpringApplication.run(CrmsystemApplication.class, args);
	}

}
