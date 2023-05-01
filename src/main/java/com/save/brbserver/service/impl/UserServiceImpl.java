package com.save.brbserver.service.impl;

import com.save.brbserver.config.ConstantFields;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.User;
import com.save.brbserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

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
	public boolean userLogin (String usernameOrEmail, String password) {
		User user = userDao.getByUsername(usernameOrEmail);
		String up = user.getPassword();
		if (password.equals(up)) {
			return userDao.userLoginUpdateTime(usernameOrEmail); //TODO encoder配置以后修改为探查密文
		}
		else
			return false;
	}
	
	@Override
	public boolean userRegister (String username, String password, String email) {
		return userDao.userRegister(username, password, email);
	}
	
	@Override
	public boolean postUserHeadSculpture (String username) {
		String time = ConstantFields.dateToString(new Date());
//		2023-05-01 17:20:16
		
		return false;
	}
	
	@Override
	public boolean changePassword (String username, String password) {
		
		return false;
	}
	
}
