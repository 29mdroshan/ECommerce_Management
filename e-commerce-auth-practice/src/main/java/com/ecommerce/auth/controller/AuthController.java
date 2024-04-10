package com.ecommerce.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth.model.UserInfo;
import com.ecommerce.auth.repository.UserInfoRepository;
import com.ecommerce.auth.service.UserInfoService;



@RestController
public class AuthController {
	@Autowired
	UserInfoService service;
	
	@PostMapping("/ecommerce/auth/register")
	public UserInfo saveUser(@RequestBody UserInfo user) {
		
		return service.registerUser(user);
		
	}
	

	@GetMapping("/ecommerce/auth/sample")
	public String sampleGet() {
		
		return "Hello sample";
		
	}
	
	@GetMapping("/ecommerce/auth/getDetails")
	public UserInfo getUserDetails() {
		return service.getUser();	}
	
//	@GetMapping("/ecommerce/getuser/sample2")
//	public UserInfo getUserInfo(@PathVariable int userId) {
//		
//	}
	
}
