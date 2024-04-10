package com.ecommerce.customer.controller;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.customer.dto.ErrorResponse;
import com.ecommerce.customer.exception.CustomerAllReadyPresentException;
import com.ecommerce.customer.exception.CustomerNotPresentException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorController {
	@ExceptionHandler(value= {CustomerAllReadyPresentException.class})
	public ResponseEntity<ErrorResponse> handleEmptyCart(Exception ex,HttpServletRequest request) {
		
		HttpStatus httpStatus= HttpStatus.NOT_FOUND;
		LocalDateTime timestamp=LocalDateTime.now();
		int status = httpStatus.value();
		String error =httpStatus.getReasonPhrase();
		String message=ex.getMessage();
		String path=request.getRequestURI();
		
		ErrorResponse body=new ErrorResponse(timestamp,status,error,message,path);
		return ResponseEntity.status(httpStatus).body(body);
		
	}
	
	@ExceptionHandler(value= {CustomerNotPresentException.class})
	public ResponseEntity<ErrorResponse> handleCustomerNotPresent(Exception ex,HttpServletRequest request) {
		
		HttpStatus httpStatus= HttpStatus.NOT_FOUND;
		LocalDateTime timestamp=LocalDateTime.now();
		int status = httpStatus.value();
		String error =httpStatus.getReasonPhrase();
		String message=ex.getMessage();
		String path=request.getRequestURI();
		
		ErrorResponse body=new ErrorResponse(timestamp,status,error,message,path);
		return ResponseEntity.status(httpStatus).body(body);
		
	}
	
	
}
