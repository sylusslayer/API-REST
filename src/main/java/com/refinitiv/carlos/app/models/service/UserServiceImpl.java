package com.refinitiv.carlos.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.refinitiv.carlos.app.models.dao.IAccountDAO;
import com.refinitiv.carlos.app.models.dao.IUserDAO;
import com.refinitiv.carlos.app.models.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDao;
	
	@Autowired
	private IAccountDAO accountDao;

	@Override
	@Transactional(readOnly=true)
	public User findOneUser(Long userId) {
		return userDao.findById(userId).orElse(null);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> findAllUsers() {
		return (List<User>) userDao.findAll();
	}
	
	@Override
	@Transactional
	public User createUser(User user) {
		User savedUser = userDao.save(user);
		if (!CollectionUtils.isEmpty(user.getAccountList())) {
			user.getAccountList().forEach(acc -> {
				acc.setUser(savedUser);
				accountDao.save(acc);
			});
		}
		return savedUser;
	}

	@Override
	@Transactional
	public void deleteUser(Long userId) {
		userDao.deleteById(userId);
	}

}
