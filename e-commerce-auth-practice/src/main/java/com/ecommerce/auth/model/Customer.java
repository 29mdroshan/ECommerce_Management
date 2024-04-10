package com.ecommerce.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
	
	@Indexed(unique=true)
	private int customerId;
	private String customerName;
	@Id
	@Indexed(unique=true)
	private String customerEmail;
	private long customerPhone;
	private String address;
}
