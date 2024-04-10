package com.library.ecommerse.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.library.ecommerse.cart.model.ShoppingCart;

public interface ShppingCartRepository extends MongoRepository<ShoppingCart, Integer> {

	ShoppingCart findByCustomerId(int customer_id);

	boolean existsByCustomerId(int customerId);

}
