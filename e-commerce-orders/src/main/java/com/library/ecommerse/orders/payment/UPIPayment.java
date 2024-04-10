package com.library.ecommerse.orders.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;

import com.library.ecommerse.orders.model.PaymentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UPIPayment extends PaymentMethod {

	private String upiId;
	
	public UPIPayment(PaymentDetails paymentDetails) {
		super.paymentDetails=paymentDetails;
	}
	
	
	@Override
	public PaymentMethod processPayment() {
	
		log.info("UPI payment made:" + paymentDetails.getUpiDetails());
		return paymentDetails.getUpiDetails();
	}



	
	
//	@Autowired
//	PaymentDetails paymentDetails;

	

//	@Override
//	public PaymentMethod processPayment() {
////		log.info("UPI payment made:" + paymentDetails.getUpiDetails());
////		System.out.println("UPI payment made:"+paymentDetails.getUpiDetails());
//	return paymentDetails.getUpiDetails();// use logger
//	}

}
