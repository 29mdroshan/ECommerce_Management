package com.ecommerce.customer.exception;

public class CustomerNotPresentException extends RuntimeException{

	public CustomerNotPresentException(String msg) {
		super(msg);
	}
}
