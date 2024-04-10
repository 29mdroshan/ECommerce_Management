package com.ecommerce.auth.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Component
public class UserInfo {
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private int userId;
	@Indexed(unique = true)
	private String email;
	private String password;
}
