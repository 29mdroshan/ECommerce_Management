package com.ecommerce.customer.service;

import java.util.List;

import com.ecommerce.customer.exception.CustomerAllReadyPresentException;
import com.ecommerce.customer.exception.CustomerNotPresentException;
import com.ecommerce.customer.model.Customer;


public interface CustomerService {
	public Customer saveCustomer(Customer customer) throws CustomerAllReadyPresentException, CustomerNotPresentException;

	public Customer findByCustomerId(int customer_id);

	public void deleteCustomer(int customer_id);

	public List<Customer> getAllCustomer();

}
