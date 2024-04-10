package com.ecommerce.customer.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@WebMvcTest
class CustomerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CustomerService service;
	
	ObjectMapper mapper=new ObjectMapper();
	ObjectWriter writter=mapper.writer();
	List<Customer> list= new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		list.add( new Customer(1,"Roshan","roshan@gmail.com",123456789,"Mysore"));
		list.add(new Customer(2,"Yash","yash@gmail.com",85269129,"Bangalore"));
		list.add(new Customer(3,"Arun","arun@gmail.com",758196757,"Delhi"));
		Collections.sort(list,(c1,c2)->c1.getCustomerId()-c2.getCustomerId());
		
	}

	@Test
	void testSaveCustomer() throws Exception {
		Customer customer1=new Customer(1,"Roshan","roshan@gmail.com",123456789,"Mysore");
		
		String customerAsString=writter.writeValueAsString(customer1);
		
		when(service.saveCustomer(customer1)).thenReturn(customer1);
		mockMvc.perform(MockMvcRequestBuilders.post("/ecommerse/customer").contentType(MediaType.APPLICATION_JSON).content(customerAsString))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customerId", Matchers.is(customer1.getCustomerId())))
		.andExpect(jsonPath("$.customerEmail", Matchers.is(customer1.getCustomerEmail())))
		.andExpect(MockMvcResultMatchers.content().string(customerAsString));
	}

	@Test
	void testFindByCustomerId() throws Exception {
		Customer customer1=new Customer(1,"Roshan","roshan@gmail.com",123456789,"Mysore");
		
		when(service.findByCustomerId(1)).thenReturn(customer1);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ecommerse/customer/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.customerName").value(customer1.getCustomerName()))
				.andExpect(jsonPath("$.customerEmail",Matchers.is(customer1.getCustomerEmail())));
	}

	@Test
	void testDeleteCustomer() throws Exception {
		Mockito.doNothing().when(service).deleteCustomer(anyInt());
		mockMvc.perform(MockMvcRequestBuilders.delete("/ecommerse/customer/{customer_id}",1)
				.accept(MediaType.APPLICATION_JSON))
            	.andExpect(status().isOk());
	}

	@Test
	void testGetAllCustomer() throws Exception {
		when(service.getAllCustomer()).thenReturn(list);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/ecommerse/customer").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(3)))
		.andExpect(jsonPath("$").isNotEmpty())
		.andExpect(jsonPath("$[1]").value(list.get(1)))
		.andExpect(jsonPath("$[2].customerName",Matchers.is(list.get(2).getCustomerName())))
		.andExpect(jsonPath("$[0].address", Matchers.is(list.get(0).getAddress())));
	}

}
