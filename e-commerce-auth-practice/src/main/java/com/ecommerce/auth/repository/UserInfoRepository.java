package com.ecommerce.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.auth.model.UserInfo;

public interface UserInfoRepository extends MongoRepository<UserInfo, Integer> {

	boolean existsByEmail(String email);

	UserInfo findByEmail(String email);

}
