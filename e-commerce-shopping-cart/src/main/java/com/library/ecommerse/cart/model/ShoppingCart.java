package com.library.ecommerse.cart.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ShoppingCart {
	@Id
	private int customerId;
	private List<Products> cart;

}
