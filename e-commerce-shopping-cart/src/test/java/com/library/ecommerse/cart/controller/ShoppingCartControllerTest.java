package com.library.ecommerse.cart.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.library.ecommerse.cart.model.Customer;
import com.library.ecommerse.cart.model.Products;
import com.library.ecommerse.cart.model.ShoppingCart;
import com.library.ecommerse.cart.service.ShoppingCartService;

@WebMvcTest
class ShoppingCartControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShoppingCartService service;
	
	
	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writer();
	
	

	@Test
	void testAddItem() throws Exception {
		Customer customer = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
		Products product1 = new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt",2);
		Products product2=new Products(2,"TShirt","Clothing",4000,"Radster tshirt",2);
		ShoppingCart cart = new ShoppingCart(customer.getCustomerId(), List.of(product1,product2));
		
		String cartAsString = writer.writeValueAsString(cart);
		
		when(service.addItem(1,1)).thenReturn(new ShoppingCart(customer.getCustomerId(), List.of(product1)));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/ecommerse/cart/{customer_id}/{product_id}",1,1).contentType(MediaType.APPLICATION_JSON).content(cartAsString))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customerId", Matchers.is(customer.getCustomerId())))
		.andExpect(jsonPath("$.cart[0]").value(product1));
	}

	@Test
	void testGetItemByCustomerId() throws Exception {
		Customer customer = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
		Products product1 = new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt",2);
		Products product2=new Products(2,"TShirt","Clothing",4000,"Radster tshirt",3);
		ShoppingCart cart = new ShoppingCart(customer.getCustomerId(), List.of(product1,product2));
		
		when(service.getItemByCustomerId(1)).thenReturn(cart);
		
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ecommerse/cart/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.customerId",Matchers.is(customer.getCustomerId())))
				.andExpect(jsonPath("$.cart", Matchers.hasSize(2)))
				.andExpect(jsonPath("$.cart[0].productName", Matchers.is(product1.getProductName())))
				.andExpect(jsonPath("$.cart[1]").value(product2));
				
				
	}

	@Test
	void testDeleteItem() throws Exception {
		Customer customer = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
		Products product1 = new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt",2);
		Products product2=new Products(2,"TShirt","Clothing",4000,"Radster tshirt",3);
		ShoppingCart cart = new ShoppingCart(customer.getCustomerId(), List.of(product1));
		
		when(service.deleteItem(1, 2)).thenReturn(cart);
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/ecommerse/cart/1/2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.customerId",Matchers.is(customer.getCustomerId())))
				.andExpect(jsonPath("$.cart", Matchers.hasSize(1)))
				.andExpect(jsonPath("$.cart[0].productName", Matchers.is(product1.getProductName())));
		
	}



}
