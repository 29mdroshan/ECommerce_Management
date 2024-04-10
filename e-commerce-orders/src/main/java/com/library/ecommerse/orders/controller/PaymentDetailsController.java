package com.library.ecommerse.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.ecommerse.orders.model.Orders;
import com.library.ecommerse.orders.model.PaymentDetails;
import com.library.ecommerse.orders.repository.PaymentDetailsRepository;
import com.library.ecommerse.orders.service.PaymentDetailsServiceImpl;

@RestController
@RequestMapping("/placeOrder/payment")
public class PaymentDetailsController {
	
	@Autowired
	PaymentDetailsServiceImpl service;
	
	@PostMapping
	public PaymentDetails savePaymentDetails(@RequestBody PaymentDetails paymentDetails ) {
		
		return service.savePaymentDetails(paymentDetails);
	
	}
	
	
	
	

}
