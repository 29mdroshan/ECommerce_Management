package com.library.ecommerse.orders.payment;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.library.ecommerse.orders.exception.CardExpiredException;
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
public class CardPayment extends PaymentMethod {

	private String cardNumber;
	private String cvv;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate expiryDate;
	
	public CardPayment(PaymentDetails paymentDetails) {
		super.paymentDetails=paymentDetails;
	}
	
	
	@Override
	public PaymentMethod processPayment() throws CardExpiredException {
		if(paymentDetails.getCardDetails().getExpiryDate().isBefore(LocalDate.now())) {
			throw new  CardExpiredException("Your Credit card has expired");
    	}
   
		
		log.info("Card payment made done:" + paymentDetails.getCardDetails());
		return paymentDetails.getCardDetails();
	}
}
