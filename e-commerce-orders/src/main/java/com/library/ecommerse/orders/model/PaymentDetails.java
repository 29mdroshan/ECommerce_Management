package com.library.ecommerse.orders.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.library.ecommerse.orders.payment.CardPayment;
import com.library.ecommerse.orders.payment.PaymentMethod;
import com.library.ecommerse.orders.payment.UPIPayment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Document
public class PaymentDetails {
	@Id
	private int customerId;
	private UPIPayment  upiDetails;
	private CardPayment cardDetails;
	
}
