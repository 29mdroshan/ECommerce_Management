//package com.library.ecommerse.orders.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//
//import com.library.ecommerse.orders.model.Customer;
//import com.library.ecommerse.orders.model.Orders;
//import com.library.ecommerse.orders.model.Products;
//import com.library.ecommerse.orders.service.OderService;
//
//@WebMvcTest
//class OrdersControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	private OderService service;
//	
//	
//	ObjectMapper mapper = new ObjectMapper();
//	ObjectWriter writer = mapper.writer();
//	
//
//	@Test
//	void testPlaceOrder() throws Exception {
//		Customer customer = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
//		Products product1 = new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt");
//		Products product2=new Products(2,"TShirt","Clothing",4000,"Radster tshirt");
//		
//		Orders order1 = new Orders("abcd12", customer, List.of(product1,product2), 4750);
//		when(service.placeOrder(1)).thenReturn(order1);
//		
//		String orderAsString = writer.writeValueAsString(order1);
//		
//		when(service.placeOrder(1)).thenReturn(order1);
//		
//		mockMvc.perform(MockMvcRequestBuilders.post("/ecommerse/orders/placeOrder/1").contentType(MediaType.APPLICATION_JSON).content(orderAsString))
//		.andExpect(status().isOk())
//		.andExpect(jsonPath("$").value(order1))
//		.andExpect(jsonPath("$.cutomer").value(customer))
//		.andExpect(jsonPath("$.product[0]").value(product1))
//		.andExpect(jsonPath("$.product[1].productPrice",Matchers.is(product2.getProductPrice())));
//	}
//
//	@Test
//	void testGetAllOrder() throws Exception {
//		Customer customer1 = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
//
//		Products product1=new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt");
//		Products product2=new Products(2, "TShirt", "Clothing", 4000, "Radster tshirt");
//	
//		Orders order1 = new Orders("abcd12", customer1, List.of(product1,product2), 4750);
//		when(service.getAllOrder()).thenReturn(List.of(order1));
//		
//		
//		mockMvc.perform(MockMvcRequestBuilders
//				.get("/ecommerse/orders")
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$").isNotEmpty())
//				.andExpect(jsonPath("$", Matchers.hasSize(1)))
//				.andExpect(jsonPath("$.[0].cutomer.customerName", Matchers.is(customer1.getCustomerName())))
//				.andExpect(jsonPath("$.[0].product",Matchers.hasSize(2)))
//				.andExpect(jsonPath("$.[0].product[0].productPrice",Matchers.is(product1.getProductPrice())))
//				.andExpect(jsonPath("$.[0].amount",Matchers.is(order1.getAmount())));
//				
//	}
//
//	@Test
//	void testGetOrderByCustomerID() throws Exception {
//		Customer customer = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
//
//		Products product1=new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt");
//		Products product2=new Products(2, "TShirt", "Clothing", 4000, "Radster tshirt");
//	
//		Orders order1 = new Orders("abcd12", customer, List.of(product1,product2), 4750);
//		when(service.getOrderByCustomerID(1)).thenReturn(List.of(order1));
//		
//		
//		mockMvc.perform(MockMvcRequestBuilders
//				.get("/ecommerse/orders/1")
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$").isNotEmpty())
//				.andExpect(jsonPath("$", Matchers.hasSize(1)))
//				.andExpect(jsonPath("$.[0].cutomer").value(customer))
//				.andExpect(jsonPath("$.[0].product",Matchers.hasSize(2)))
//				.andExpect(jsonPath("$.[0].product[0].productPrice",Matchers.is(product1.getProductPrice())))
//				.andExpect(jsonPath("$.[0].amount",Matchers.is(order1.getAmount())));
//				
//	}
//
//}
