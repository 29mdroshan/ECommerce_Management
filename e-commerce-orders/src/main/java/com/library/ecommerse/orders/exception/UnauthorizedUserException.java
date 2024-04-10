package com.library.ecommerse.orders.exception;

public class UnauthorizedUserException extends RuntimeException {
	public UnauthorizedUserException(String msg) {
		super(msg);
	}
}
