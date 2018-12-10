package com.test.backend.service;

import com.test.backend.model.User;

public interface UserService {
	
	public User findUserByUserName(String userName);

}
