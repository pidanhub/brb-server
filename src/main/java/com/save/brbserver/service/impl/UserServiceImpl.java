package com.save.brbserver.service.impl;

import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.User;
import com.save.brbserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Map;

/**
 * @Author:Zzs
 * @Description: implements functions in UserService
 * meanwhile, implements UserDetailService,
 * and then we can have configured permissions operations
 * @DateTime: 2023/4/29 18:56
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean userLogin (String usernameOrEmail, String password) throws SQLException {
		User user = userDao.getByUsername(usernameOrEmail);
		String up = user.getPassword();
		if (password.equals(up)) {
			return userDao.userLoginUpdateTime(usernameOrEmail); //TODO encoder配置以后修改为探查密文
		}
		else
			return false;
	}
	
	@Override
	@Transactional
	public boolean userRegister (String username, String password, String email) throws SQLException {
		return userDao.userRegister(username, password, email);
	}
	
	@Override
	public boolean postUserHeadSculpture (String username, String path) throws SQLException {
		return userDao.postUserHeadSculpture(username, path);
	}
	
	@Override
	public boolean changePassword (String username, String password) {
		
		return false;
	}
	
	@Override
	public Long getUserIdByName (String username) throws SQLException {
		return userDao.getUserIdByName(username);
	}
	
	@Override
	public Map<String, Object> getSimpleUserInfo (String username) throws SQLException {
		return userDao.getSimpleUserInfo(userDao.getUserIdByName(username));
	}
	
}
