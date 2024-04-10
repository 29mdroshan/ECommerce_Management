package com.ecommerce.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ECommerceCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceCustomerApplication.class, args);
	}

}
