package com.ecommerce.auth.exception;

public class EmailAlreadyPresentException extends RuntimeException{
	public EmailAlreadyPresentException(String msg) {
		super(msg);
	}
}
