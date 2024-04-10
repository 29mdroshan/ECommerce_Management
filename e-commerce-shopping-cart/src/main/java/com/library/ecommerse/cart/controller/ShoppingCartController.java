package com.library.ecommerse.cart.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.library.ecommerse.cart.model.ShoppingCart;
import com.library.ecommerse.cart.service.ShoppingCartService;

@RestController
@RequestMapping("/ecommerse/cart")
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartService service;
	
	@Autowired
	RestTemplate rt;
	
	@PostMapping("/{customer_id}/{product_id}")
	public ShoppingCart addItem(@PathVariable("customer_id") int customer_id,@PathVariable("product_id") int product_id) {
		return service.addItem(customer_id, product_id);
	}

	@GetMapping("/{customer_id}")
	public ShoppingCart getItemByCustomerId(@PathVariable("customer_id") int customer_id) {
		return service.getItemByCustomerId(customer_id);
	}

	@DeleteMapping("/{customer_id}/{product_id}")
	public ShoppingCart deleteItem(@PathVariable("customer_id") int customer_id,@PathVariable("product_id") int product_id) {
		return service.deleteItem(customer_id, product_id);
	}
	
	@DeleteMapping("/clearCart/{customer_id}")
	public void clearShoppingCart(@PathVariable("customer_id") int customer_id) {
		service.deleteCartByCustomerID(customer_id);
	}
	
	
	
	

}
