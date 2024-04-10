package com.library.ecommerse.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.library.ecommerse.orders.model.PaymentDetails;

public interface PaymentDetailsRepository extends MongoRepository<PaymentDetails, Integer> {

	PaymentDetails findByCustomerId(int customer_id);

}
