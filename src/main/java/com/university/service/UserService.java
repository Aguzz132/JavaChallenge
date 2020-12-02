package com.university.service;

import com.university.model.User;

public interface UserService {
	
	public User findUserByUserName(String userName);
	
	public void saveUser(User user);
}
