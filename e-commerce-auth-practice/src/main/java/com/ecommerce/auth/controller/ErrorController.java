package com.ecommerce.auth.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.auth.dto.ErrorResponse;
import com.ecommerce.auth.exception.EmailAlreadyPresentException;



@RestControllerAdvice
public class ErrorController {
	@ExceptionHandler(value= {EmailAlreadyPresentException.class})
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
	
	
}
