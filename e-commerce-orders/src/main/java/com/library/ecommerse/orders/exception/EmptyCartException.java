package com.library.ecommerse.orders.exception;

public class EmptyCartException extends RuntimeException{
	public EmptyCartException(String message){
		super(message);
	}
	
}
