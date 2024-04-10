package com.ecommerce.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.service.CustomerService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/ecommerse/customer")
public class CustomerController {
	@Autowired
	CustomerService service;
	
	@Operation(summary = "Saving customer", responses = {
			@ApiResponse(description = "customer Detail saved Sucessfully", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))),
			@ApiResponse(description = "customer not saved", responseCode = "404", content = @Content(mediaType = "application/json")) })
	@PostMapping
	public Customer saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}
	
	@GetMapping("/{customer_id}")
	@Operation(summary = "Getting customer by customer Id", responses = {
			@ApiResponse(description = "customer Detail ", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))),
			@ApiResponse(description = "customer Details not fond", responseCode = "404", content = @Content()) })
	public Customer findByCustomerId(@PathVariable("customer_id") int customer_id) {
		return service.findByCustomerId(customer_id);
	}
	

	@DeleteMapping("/{customer_id}")
	@Operation(summary = "Saving customer", responses = {
			@ApiResponse(description = "customer Detail deleted Sucessfully", responseCode = "200", content = @Content(mediaType = "application/json")),
			@ApiResponse(description = "customer not deleted", responseCode = "404", content = @Content) })
	public void deleteCustomer(@PathVariable("customer_id")  int customer_id) {
		service.deleteCustomer(customer_id);
	}

	@GetMapping
	@Operation(summary = "Getting All customer ", responses = {
			@ApiResponse(description = "customer Detail ", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))),
			@ApiResponse(description = "customer Details not fond", responseCode = "404", content = @Content()) })
	public List<Customer> getAllCustomer(){
		return service.getAllCustomer();
	}
	
}
