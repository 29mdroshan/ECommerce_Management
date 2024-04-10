package com.library.ecommerse.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.ecommerse.orders.config.SecurityConfiguration;
import com.library.ecommerse.orders.exception.UnauthorizedUserException;
import com.library.ecommerse.orders.model.PaymentDetails;
import com.library.ecommerse.orders.repository.PaymentDetailsRepository;

@Service

public class PaymentDetailsServiceImpl {

	@Autowired
	PaymentDetailsRepository repo;
	
	@Autowired
	SecurityConfiguration config;
	
	
	public PaymentDetails savePaymentDetails(PaymentDetails paymentDetail ) throws UnauthorizedUserException {
		if(config.user.getBody().getUserId() == paymentDetail.getCustomerId()) {
			return repo.save(paymentDetail);
		}
		throw new UnauthorizedUserException("Please login into your account");
	}
	
}
