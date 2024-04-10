package com.ecommerce.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ecommerce.customer.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {

	Customer findAllByCustomerId(int customer_id);

	boolean existsByCustomerEmail(String customerEmail);

	Customer findByCustomerEmail(String customerEmail);

}
