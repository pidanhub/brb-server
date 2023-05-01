package com.save.brbserver.service.impl;

import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.User;
import com.save.brbserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author:Zzs
 * @Description: implements functions in UserService
 *              meanwhile, implements UserDetailService,
 *              and then we can have configured permissions operations
 * @DateTime: 2023/4/29 18:56
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User getByUsername (String username) {
		return userDao.getByUsername(username);
	}
	
	@Override
	public boolean userRegister(String username, String password, String email) {
		return userDao.userRegister(username, password, email);
	}
	
}
