package com.fis.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements ExternalUserService {

	public String getUserData() {
		return "Hello from EXTERNAL user ";
	}

}
