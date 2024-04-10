package com.library.ecommerse.orders.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class Products {
	@Id
	private int productId;
	private String productName;
	private String productCategory;
	private int productPrice;
	private String productDesc;
	private int quantity;
	
	

}
