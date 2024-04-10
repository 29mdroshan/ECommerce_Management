package com.library.ecommerse.orders.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.library.ecommerse.orders.payment.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Document
public class Orders {
	@Id
	private String orderId;
	private Customer cutomer;
	private List<Products> product;
	private PaymentMethod PaymentInfo;
	private double amount;
	
	
	
}
