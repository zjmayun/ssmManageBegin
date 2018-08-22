package org.service.impl;

import org.bean.User;
import org.dao.UserDao;
import org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public boolean validate(User user) {
        User userO=userDao.selectByNameAndPass(user);
		return userO==null;
	}
	


	

}
