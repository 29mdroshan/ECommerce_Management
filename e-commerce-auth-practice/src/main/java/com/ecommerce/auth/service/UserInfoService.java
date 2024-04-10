package com.ecommerce.auth.service;

import com.ecommerce.auth.model.UserInfo;

public interface UserInfoService {

	public UserInfo registerUser(UserInfo user);
	
	public UserInfo getUser();
	
	
}
