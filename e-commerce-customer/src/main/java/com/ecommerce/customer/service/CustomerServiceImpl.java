package com.ecommerce.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.customer.exception.CustomerAllReadyPresentException;
import com.ecommerce.customer.exception.CustomerNotPresentException;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository repo;
	
	@Autowired
	Customer cust;

	
	@Override
	public Customer saveCustomer(Customer customer)  throws CustomerAllReadyPresentException, CustomerNotPresentException {
		if(repo.existsByCustomerEmail(customer.getCustomerEmail()))	{
			cust=repo.findByCustomerEmail(customer.getCustomerEmail());
			customer.setCustomerId(cust.getCustomerId());
			return repo.save(customer);
		}
		else if(customer.getCustomerId()!=0 && !repo.existsByCustomerEmail(customer.getCustomerEmail()) ) {
			return repo.save(customer);
		}
		throw new CustomerNotPresentException("Please Register first to update your profile");
		
	}

	@Override
	public Customer findByCustomerId(int customer_id) {
		
		return repo.findAllByCustomerId(customer_id);
	}

	@Override
	public void deleteCustomer(int customer_id) {
		repo.deleteById(customer_id);

	}

	@Override
	public List<Customer> getAllCustomer() {
		
		return repo.findAll();
	}

}
