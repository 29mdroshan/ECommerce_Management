package com.ecommerce.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.auth.config.JwtAuthenticationFilter;
import com.ecommerce.auth.exception.EmailAlreadyPresentException;
import com.ecommerce.auth.model.Customer;
import com.ecommerce.auth.model.UserInfo;
import com.ecommerce.auth.repository.UserInfoRepository;



@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	UserInfoRepository repo;
	
	@Autowired
	RestTemplate rt;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	JwtAuthenticationFilter filter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	
	@Override
	public UserInfo registerUser(UserInfo user) {
		if(repo.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyPresentException("Your Email Already Registered");
		}
		user.setUserId((int)sequenceGeneratorService.generateSequence(UserInfo.SEQUENCE_NAME));
		
		String password = user.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		
		Customer customer=new Customer();
		customer.setCustomerId(user.getUserId());
		customer.setCustomerEmail(user.getEmail());
		rt.postForObject("http://localhost:7200/ecommerse/customer", customer, Customer.class);
		return repo.save(user);
	}




	@Override
	public UserInfo getUser() {
		return repo.findByEmail(filter.userMial);
	}

}
