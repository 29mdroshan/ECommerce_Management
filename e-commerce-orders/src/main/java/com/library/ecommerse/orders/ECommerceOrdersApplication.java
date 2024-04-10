package com.library.ecommerse.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ECommerceOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceOrdersApplication.class, args);
	}
	
	@Bean
	public RestTemplate getREstTemplateBean() {
		return new RestTemplate();
	}

}
