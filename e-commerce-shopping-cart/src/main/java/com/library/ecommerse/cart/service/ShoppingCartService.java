package com.library.ecommerse.cart.service;

import java.util.concurrent.CompletableFuture;

import com.library.ecommerse.cart.model.ShoppingCart;
import com.library.ecommerse.exception.ShoppingCartException;

public interface ShoppingCartService {

	public ShoppingCart addItem(int customer_id,int product_id) throws ShoppingCartException;
	
	public ShoppingCart getItemByCustomerId(int customer_id);
	
	public ShoppingCart deleteItem(int customer_id,int product_id) throws ShoppingCartException;
	
	public void deleteCartByCustomerID(int customer_Id);
	
	//public ShoppingCart palceOrderOfCustomer(int customer_id) ;
	
	
}
