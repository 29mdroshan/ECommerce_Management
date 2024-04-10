package com.ecommerce.customer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.customer.exception.CustomerAllReadyPresentException;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.repository.CustomerRepository;



@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	CustomerRepository repo;
	
	@InjectMocks
	CustomerServiceImpl service;
	
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void testSaveCustomer() {
		Customer customer=new Customer(1,"Roshan","roshan@gmail.com",123456789,"Mysore");
		Customer customer2=new Customer(0,"Roshan","roshan@gmail.com",123456789,"Mysore");
		
		when(repo.save(customer)).thenReturn(customer);
		
		assertEquals(customer, service.saveCustomer(customer));
		
		assertNotEquals("roshan@gmailcom", service.saveCustomer(customer).getCustomerEmail());
		
		assertNotNull(service.saveCustomer(customer));
		
		verify(repo,atLeast(3)).save(any(Customer.class));
		
		verifyNoMoreInteractions(repo);
		
	}
	


	@Test
	void testFindByCustomerId() {
		Customer customer1=new Customer(1,"Roshan","roshan@gmail.com",123456789,"Mysore");
		Customer customer2=new Customer(2,"Yash","yash@gmail.com",85269129,"Bangalore");
		
		when(repo.findAllByCustomerId(1)).thenReturn(customer1);
		lenient().when(repo.findAllByCustomerId(2)).thenReturn(customer2);
		
		assertEquals(customer1, service.findByCustomerId(1));
		
		assertNull(service.findByCustomerId(5));
		
		assertTrue(service.findByCustomerId(2).getCustomerName()=="Yash");
		
		verify(repo,atMost(3)).findById(any(Integer.class));
	}

	@Test
	void testDeleteCustomer() {
		service.deleteCustomer(anyInt());
		verify(repo,times(1)).deleteById(any()); 	
		
	}

	@Test
	void testGetAllCustomer() {
		Customer customer1=new Customer(1,"Roshan","roshan@gmail.com",123456789,"Mysore");
		Customer customer2=new Customer(2,"Yash","yash@gmail.com",85269129,"Bangalore");
		
		
		when(repo.findAll()).thenReturn(List.of(customer1,customer2));
		
		assertEquals(List.of(customer1,customer2), service.getAllCustomer());
		
		assertNotNull(service.getAllCustomer());
		assertNotEquals(10, service.getAllCustomer().size());
		
		assertEquals(2, service.getAllCustomer().size());
		
		assertEquals(customer1, service.getAllCustomer().get(0));
		
		verify(repo,atLeast(5)).findAll();
	}

}
