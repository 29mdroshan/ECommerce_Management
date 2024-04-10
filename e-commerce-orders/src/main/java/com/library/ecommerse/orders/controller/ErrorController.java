package com.library.ecommerse.orders.controller;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.library.ecommerse.orders.dto.ErrorResponse;
import com.library.ecommerse.orders.exception.CardExpiredException;
import com.library.ecommerse.orders.exception.EmptyCartException;
import com.library.ecommerse.orders.exception.UnauthorizedUserException;
import com.library.ecommerse.orders.exception.UpdateProfileException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorController {
	@ExceptionHandler(value= {EmptyCartException.class})
	public ResponseEntity<com.library.ecommerse.orders.dto.ErrorResponse> handleEmptyCart(Exception ex,HttpServletRequest request) {
		
		HttpStatus httpStatus= HttpStatus.NOT_FOUND;
		LocalDateTime timestamp=LocalDateTime.now();
		int status = httpStatus.value();
		String error =httpStatus.getReasonPhrase();
		String message=ex.getMessage();
		String path=request.getRequestURI();
		
		ErrorResponse body=new ErrorResponse(timestamp,status,error,message,path);
		return ResponseEntity.status(httpStatus).body(body);
		
	}
	
	@ExceptionHandler(value= {UnauthorizedUserException.class})
	public ResponseEntity<com.library.ecommerse.orders.dto.ErrorResponse> unauthorizeUser(Exception ex,HttpServletRequest request) {
		
		HttpStatus httpStatus= HttpStatus.NOT_FOUND;
		LocalDateTime timestamp=LocalDateTime.now();
		int status = httpStatus.value();
		String error =httpStatus.getReasonPhrase();
		String message=ex.getMessage();
		String path=request.getRequestURI();
		
		ErrorResponse body=new ErrorResponse(timestamp,status,error,message,path);
		return ResponseEntity.status(httpStatus).body(body);
		
	}
	
	@ExceptionHandler(value= {CardExpiredException.class})
	public ResponseEntity<com.library.ecommerse.orders.dto.ErrorResponse> handelExpiryCardexception(Exception ex,HttpServletRequest request) {
		
		HttpStatus httpStatus= HttpStatus.NOT_ACCEPTABLE;
		LocalDateTime timestamp=LocalDateTime.now();
		int status = httpStatus.value();
		String error =httpStatus.getReasonPhrase();
		String message=ex.getMessage();
		String path=request.getRequestURI();
		
		ErrorResponse body=new ErrorResponse(timestamp,status,error,message,path);
		return ResponseEntity.status(httpStatus).body(body);
		
	}
	
	
	@ExceptionHandler(value= {UpdateProfileException.class})
	public ResponseEntity<com.library.ecommerse.orders.dto.ErrorResponse> handelUpdateProfileException(Exception ex,HttpServletRequest request) {
		
		HttpStatus httpStatus= HttpStatus.NOT_ACCEPTABLE;
		LocalDateTime timestamp=LocalDateTime.now();
		int status = httpStatus.value();
		String error =httpStatus.getReasonPhrase();
		String message=ex.getMessage();
		String path=request.getRequestURI();
		
		ErrorResponse body=new ErrorResponse(timestamp,status,error,message,path);
		return ResponseEntity.status(httpStatus).body(body);
		
	}
	
	
	
}
