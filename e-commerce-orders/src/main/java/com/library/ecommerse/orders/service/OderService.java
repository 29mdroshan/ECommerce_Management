package com.library.ecommerse.orders.service;

import java.util.List;

import com.library.ecommerse.orders.exception.CardExpiredException;
import com.library.ecommerse.orders.exception.EmptyCartException;
import com.library.ecommerse.orders.exception.UpdateProfileException;
import com.library.ecommerse.orders.model.Orders;
import com.library.ecommerse.orders.model.ShoppingCart;


public interface OderService {
	
	public Orders placeOrder(int customer_id,String paymentType) throws EmptyCartException,UpdateProfileException, CardExpiredException;
	
	public List<Orders> getAllOrder();
	
	public List<Orders> getOrderByCustomerID(int customer_Id);
	
	public Orders getOrderByID(String order_id);
	
	

}
