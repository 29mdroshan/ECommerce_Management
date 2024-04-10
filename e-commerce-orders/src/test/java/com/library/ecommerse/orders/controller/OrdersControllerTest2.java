//package com.library.ecommerse.orders.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
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
//import com.library.ecommerse.orders.model.Customer;
//import com.library.ecommerse.orders.model.Orders;
//import com.library.ecommerse.orders.model.Products;
//import com.library.ecommerse.orders.payment.UpiPaymentMethod;
//import com.library.ecommerse.orders.service.OderService;
//import com.library.ecommerse.orders.service.OrdersPDFExporter;
//
//@WebMvcTest
//@EnableWebMvc
//class OrdersControllerTest2 {
//
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	private OderService service;
//	
//	@MockBean
//	OrdersPDFExporter exporter;
//	
//	
//	ObjectMapper mapper = new ObjectMapper();
//	ObjectWriter writer = mapper.writer();
//	
//	Customer customer1;
//	Products product1;
//	Products product2;
//	Orders order1;
//	UpiPaymentMethod upi;
//	
//	
//	@BeforeEach
//	public void setUp() {
//		 customer1 = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
//		 product1 = new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt",2);
//		 product2=new Products(2,"TShirt","Clothing",4000,"Radster tshirt",2);
//		
//		 upi=new UpiPaymentMethod("bob", "roshan@bob");
//		 order1 = new Orders("abcd12", customer1, List.of(product1,product2),upi, 4750);
//	}
//	
//	@Test
//	void testGetAllOrder() throws Exception {
//
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
//	}
//
////	@Test
////	void testGetOrderByCustomerID() {
////		fail("Not yet implemented");
////	}
//
//	
//
//}
