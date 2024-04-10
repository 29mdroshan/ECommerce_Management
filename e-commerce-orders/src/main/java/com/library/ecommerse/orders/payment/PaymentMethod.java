package com.library.ecommerse.orders.payment;

import java.beans.JavaBean;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;

import com.library.ecommerse.orders.model.PaymentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
public abstract class PaymentMethod {// interface

	@Transient
	public static PaymentDetails paymentDetails;

	public abstract PaymentMethod processPayment();

}
