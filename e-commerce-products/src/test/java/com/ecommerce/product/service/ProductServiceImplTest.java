package com.ecommerce.product.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.product.model.Products;
import com.ecommerce.product.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	ProductRepository repo;
	
	@InjectMocks
	ProductServiceImpl service;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Products product1=new Products(1,"Shirt","Clothing",750,"Campus Checks pattern shirt");
	}
	
	
	@Test
	void testSaveProduct() {
		Products product1=new Products(1,"Shirt","Clothing",750,"Campus Checks pattern shirt");
		when(repo.save(product1)).thenReturn(product1);
		
		assertEquals(product1, service.saveProduct(product1));
		
		assertNotEquals(800, service.saveProduct(product1).getProductPrice());
		
		assertNotNull(service.saveProduct(product1));
		
		verify(repo,atLeast(1)).save(any(Products.class));
		
		verifyNoMoreInteractions(repo);
		
		
				
	}

//	@Order(4)
	@Test
	void testDeleteProduct() {

		service.deleteProduct(1);
		verify(repo,times(1)).deleteById(any()); 	
		
	}


	@Test
	void testGetAllProducts() {
		Products product1=new Products(1,"Shirt","Clothing",750,"Campus Checks pattern shirt");
		Products product2=new Products(2,"TShirt","Clothing",4000,"Radster tshirt");
		
		when(repo.findAll()).thenReturn(List.of(product1,product1));
		
		assertEquals(List.of(product1,product1), service.getAllProducts());
		
		assertNotNull(service.getAllProducts());
		assertNotEquals(10, service.getAllProducts());
		
		assertEquals(2, service.getAllProducts().size());
		
		assertEquals(product1, service.getAllProducts().get(0));
		
		verify(repo,atLeast(5)).findAll();
		
	}

	@Test
	void testFindByProductId() {
		Products product1=new Products(1,"Shirt","Clothing",750,"Campus Checks pattern shirt");
		
		Products product2=new Products(2,"TShirt","Clothing",4000,"Radster tshirt");
		
		when(repo.findByProductId(1)).thenReturn(product1);
		
		assertEquals(product1, service.findByProductId(1));
		
		assertNull(service.findByProductId(5));
		
		assertTrue(service.findByProductId(1).getProductName()=="Shirt");
		
		verify(repo,atMost(3)).findById(any(Integer.class));
		
	}


	

}
