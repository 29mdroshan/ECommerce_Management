package com.library.ecommerse.orders.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.library.ecommerse.orders.model.Orders;

public interface PlacedOrderRepository extends MongoRepository<Orders, String> {

	Orders findByOrderId(String order_id);

}
