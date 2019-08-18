package com.refinitiv.carlos.app.models.service;

import java.util.List;

import com.refinitiv.carlos.app.models.entity.User;

public interface IUserService {
	
	public List<User> findAllUsers();
	
	public User findOneUser(Long userId);
	
	public User createUser(User user);
	
	public void deleteUser(Long userId);
}
