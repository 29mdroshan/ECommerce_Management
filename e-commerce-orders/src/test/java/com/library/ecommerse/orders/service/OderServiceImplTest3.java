//package com.library.ecommerse.orders.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import com.library.ecommerse.orders.config.SecurityConfiguration;
//import com.library.ecommerse.orders.model.Customer;
//import com.library.ecommerse.orders.model.Orders;
//import com.library.ecommerse.orders.model.Products;
//import com.library.ecommerse.orders.model.UserInfo;
//import com.library.ecommerse.orders.payment.CreditCardPaymentMethod;
//import com.library.ecommerse.orders.payment.PaymentMethod;
//import com.library.ecommerse.orders.payment.UpiPaymentMethod;
//import com.library.ecommerse.orders.repository.PlacedOrderRepository;
//
//@ExtendWith(MockitoExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class OderServiceImplTest3 {
//	@Mock
//	PlacedOrderRepository repo;
//
//	@InjectMocks
//	OderServiceImpl service;
//
//	@Mock
//	RestTemplate rt;
//
//	Customer customer1;
//	Customer customer2;
//
//	Products product1;
//	Products product2;
//
//	Orders order1;
//	Orders order2;
//
//	UpiPaymentMethod upi;
//	CreditCardPaymentMethod card;
//
//	@BeforeEach
//	public void setUp() {
//		upi = new UpiPaymentMethod("bob", "roshan@bob");
//		card = new CreditCardPaymentMethod("sbi", "123456789", "1234", LocalDate.of(2024, 06, 01));
//		customer1 = new Customer(1, "Roshan", "roshan@gmail.com", 123456789, "Mysore");
//		customer2 = new Customer(2, "Yash", "yash@gmail.com", 85269129, "Bangalore");
//		product1 = new Products(1, "Shirt", "Clothing", 750, "Campus Checks pattern shirt", 2);
//		product2 = new Products(2, "TShirt", "Clothing", 4000, "Radster tshirt", 2);
//		order1 = new Orders("abcd12", customer1, List.of(product1, product2), upi, 4750);
//		order2 = new Orders("efgh12", customer2, List.of(product2), card, 4000);
//
//	}
//
//	private PaymentMethod UpiPaymentMethod(String string, String string2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	@Test
////	void testPlaceOrderByUpi() {
////		UpiPaymentMethod payment=new UpiPaymentMethod("Bob","roshna@bob");
////		order = new Orders("abcd12", customer, List.of(product),payment, 750);
////	
////	
////		
////		when(service.rt.getForObject("http://localhost:7200/ecommerse/customer/1", Customer.class)).thenReturn(customer);
////		when(repo.save(order)).thenReturn(order);
////		
////		assertEquals(order, service.placeOrderByUpi(1, payment));
////	}
//
////	@Test
////	void testPlaceOrderByCard() {
////		fail("Not yet implemented");
////	}
//	
//	
//	@Test
//	void testGetAllOrder() {
//
//		when(repo.findAll()).thenReturn(List.of(order1, order2));
//
//		assertEquals(List.of(order1, order2), service.getAllOrder());
//
//		assertNotNull(service.getAllOrder());
//		assertNotEquals(10, service.getAllOrder().size());
//
//		assertEquals(2, service.getAllOrder().size());
//
//		assertEquals(order1, service.getAllOrder().get(0));
//
//		assertEquals(4000, service.getAllOrder().get(1).getAmount());
//
//		assertEquals("Roshan", service.getAllOrder().get(0).getCutomer().getCustomerName());
//
//	}
//
//	@Test
//	void testGetOrderByCustomerID() {
//		when(repo.findAll()).thenReturn(List.of(order1));
//
//		assertEquals(List.of(order1), service.getOrderByCustomerID(1));
//
//		assertNotNull(service.getOrderByCustomerID(1));
//
//		assertEquals(2, service.getOrderByCustomerID(1).get(0).getProduct().size());
//
//		assertEquals("Roshan", service.getOrderByCustomerID(1).get(0).getCutomer().getCustomerName());
//
//		assertEquals(List.of(), service.getOrderByCustomerID(2));
//
//	}
//
//	@Test
//	void testGetOrderByID() {
//		when(repo.findByOrderId(any())).thenReturn(order1);
//		
//		assertEquals(order1, service.getOrderByID("abcd12"));
//		
//		assertEquals(order1.getCutomer(), service.getOrderByID("abcd12").getCutomer());
//		
//		assertNotEquals("ahbvasj", service.getOrderByID("abcd12"));
//		
//		assertEquals(2, service.getOrderByID("abcd12").getProduct().size());
//
//	}
//
//
//}
