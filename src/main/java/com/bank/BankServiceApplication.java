package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bank.api.jms.config")
public class BankServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}
}
