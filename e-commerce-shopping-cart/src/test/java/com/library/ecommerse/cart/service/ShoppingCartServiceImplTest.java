package com.library.ecommerse.cart.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.library.ecommerse.cart.model.Customer;
import com.library.ecommerse.cart.model.Products;
import com.library.ecommerse.cart.model.ShoppingCart;
import com.library.ecommerse.cart.repository.ShppingCartRepository;
import com.library.ecommerse.exception.ShoppingCartException;
import com.mongodb.assertions.Assertions;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingCartServiceImplTest {

	@Mock
	ShppingCartRepository repo;

	@InjectMocks
	ShoppingCartServiceImpl service;

	@Mock
	RestTemplate rt;

	@Autowired
	Customer customer;

	@Autowired
	Products product1;
	
	@Autowired
	Products product2;

	@BeforeEach
	public void setUp() {
		customer = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
		product1 = new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt",2);
		product2=new Products(2,"TShirt","Clothing",4000,"Radster tshirt",1);
		
	}

	@Order(value=1)
	@Test
	void testAddItem() {
		
		ShoppingCart cart2=new ShoppingCart(customer.getCustomerId(),List.of(product1));
		
		when(service.rt.getForObject("http://localhost:7200/ecommerse/customer/" + 1, Customer.class)).thenReturn(customer);
		when(service.rt.getForObject("http://localhost:7100/ecommerse/product/" + 1,  Products.class)).thenReturn(product1);
		when(repo.save(cart2)).thenReturn(cart2);

		
		assertEquals(cart2, service.addItem(1, 1));
		assertNotNull(service.addItem(1, 1));
		assertEquals(cart2.getCustomerId(), service.addItem(1, 1).getCustomerId());
		assertEquals(cart2.getCart().size(), 1);
		assertEquals(cart2.getCart().get(0).getProductId(), product1.getProductId());

		verify(rt, atLeast(1)).getForObject("http://localhost:7200/ecommerse/customer/" + 1, Customer.class);
		verify(rt, atLeast(1)).getForObject("http://localhost:7100/ecommerse/product/1", Products.class);
		verify(repo, atLeast(1)).save(any(ShoppingCart.class));

	}
	@Order(value=2)
	@Test
	void testwhenCustomerAndProductThrowException() {

		Mockito.lenient().when(rt.getForObject(
         "http://localhost:7200/ecommerse/customer/2", Customer.class))
        .thenThrow(ShoppingCartException.class);
		
		Mockito.lenient().when(rt.getForObject(
		         "http://localhost:7200/ecommerse/customer/5", Products.class))
		        .thenThrow(ShoppingCartException.class);
		

//		assertThrows(ShoppingCartException.class,()->service.addItem(1, 2));
//		
//		assertThrows(ShoppingCartException.class, ()->service.addItem(1, 5));
		
	}

	@Order(value=2)
	@Test
	void testGetItemByCustomerId() {
		
		ShoppingCart cart=new ShoppingCart(customer.getCustomerId(),List.of(product1));

		when(repo.findByCustomerId(1)).thenReturn(cart);

		assertEquals(cart, service.getItemByCustomerId(1));

		assertNull(service.getItemByCustomerId(2));

		assertTrue(service.getItemByCustomerId(1).getCart().equals(cart.getCart()));

		verify(repo, atMost(3)).findById(any(Integer.class));
	}

	@Test
	void testDeleteItem() {
//		ShoppingCart cart=new ShoppingCart(customer.getCustomerId(),List.of(product2));
//		lenient().when(repo.findByCustomerId(1)).thenReturn(cart);
//		product1.setQuantity(1);
//		ShoppingCart cart3=new ShoppingCart(customer.getCustomerId(),List.of());
//		
//		lenient().when(service.deleteItem(1, 1)).thenThrow(ShoppingCartException.class);
//		
//		assertThrows(ShoppingCartException.class,()->service.addItem(1, 1));
//		assertEquals(cart, service.deleteItem(1, 1));
		//assertEquals(product2, service.deleteItem(1, 1).getCart().get(0));
		
		
	
	}

	@Test
	void testDeleteCartByCustomerID() {
		
		service.deleteCartByCustomerID(1);
		verify(repo,times(1)).deleteById(any());
	}

}
