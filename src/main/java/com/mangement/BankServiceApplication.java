package com.mangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bank")
public class BankServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}
}
