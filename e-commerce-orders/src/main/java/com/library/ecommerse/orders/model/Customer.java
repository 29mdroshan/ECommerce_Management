package com.library.ecommerse.orders.model;

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
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private int customerId;
	private String customerName;
	@Indexed(unique=true)
	private String customerEmail;
	@Indexed(unique=true)
	private long customerPhone;
	private String address;
}
