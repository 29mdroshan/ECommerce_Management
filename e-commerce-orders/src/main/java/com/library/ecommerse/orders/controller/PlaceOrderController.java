package com.library.ecommerse.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.ecommerse.orders.model.Orders;

import com.library.ecommerse.orders.service.OderService;
import com.library.ecommerse.orders.service.OrdersPDFExporter;

@RestController
@RequestMapping("/placeOrder")
public class PlaceOrderController {
	
	@Autowired
	OderService service;
	
	
	@PostMapping("/{paymentType}/{customer_id}")// UPI/CARD
	public Orders placeOrderByUpi(@PathVariable String paymentType,@PathVariable("customer_id") int customer_id) {
	return service.placeOrder(customer_id,paymentType);
	//service.placeOrderByUpi(customer_id);


	}
	
	@GetMapping// UPI/CARD
	public List<Orders> placeOrderByUpi() {
	return service.getAllOrder();
	//service.placeOrderByUpi(customer_id);


	}
	
	
//	@PostMapping("/card/{customer_id}")
//	public Orders placeOrderByCard(@RequestBody CreditCardPaymentMethod card,@PathVariable("customer_id") int customer_id) {
//		
//		return service.placeOrderByCard(customer_id,card);
//
//	}


}
