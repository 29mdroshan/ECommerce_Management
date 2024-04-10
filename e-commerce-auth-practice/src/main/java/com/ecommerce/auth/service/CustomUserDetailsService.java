package com.ecommerce.auth.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.model.UserInfo;
import com.ecommerce.auth.repository.UserInfoRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	UserInfoRepository repo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo user=repo.findByEmail(email);
		if(user==null)
			throw new UsernameNotFoundException("User Not found");
		return new AuthUserDetails(user) ;
		//return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
			
		}

}
