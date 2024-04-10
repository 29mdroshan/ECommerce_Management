package com.ecommerce.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ECommerceAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceAuthApplication.class, args);
	}
	
	@Bean
	public RestTemplate getREstTemplateBean() {
		return new RestTemplate();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoderBean() {
		return new BCryptPasswordEncoder();
	}

}
