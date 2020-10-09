package com.banking.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@ComponentScan("com.banking.account.*")
@EnableSwagger2
public class AccountCreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountCreationApplication.class, args);
	}

}
