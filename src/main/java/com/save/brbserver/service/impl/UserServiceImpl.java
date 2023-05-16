package com.save.brbserver.service.impl;

import com.save.brbserver.customexception.FormatException;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.User;
import com.save.brbserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
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
	@Transactional (rollbackFor = Exception.class)
	public Map<String, String> userLogin (String usernameOrEmail, String password) throws SQLException {
		User user = userDao.getByUsername(usernameOrEmail);
		if (user == null)
			return null;
		Map<String, String> map = new HashMap<>();
		map.put("id", String.valueOf(user.getUserId()));
		map.put("username", user.getUsername());
		map.put("e-mail", user.getEmail());
		map.put("introduction", user.getIntroduction());
		map.put("head-sculpture-path", user.getHeadSculpturePath());
		userDao.userLoginUpdateTime(usernameOrEmail);
		String up = user.getPassword();
		String encodePassword = DigestUtils.md5Hex(password);
		log.info(encodePassword);
		if (encodePassword.equals(up)) {
			return map;
		}
		else
			return null;
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public boolean userRegister (String username, String password, String email) throws SQLException, FormatException {
		if (!email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$"))
			throw new FormatException();
		String encodePassword = DigestUtils.md5Hex(password);
		log.info(encodePassword);
		return userDao.userRegister(username, encodePassword, email);
	}
	
	@Override
	public boolean postUserHeadSculpture (String username, String path) throws SQLException {
		return userDao.postUserHeadSculpture(username, path);
	}
	
	@Override
	public boolean changePassword (String username, String password) {
		//TODO
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
	
	@Override
	public boolean setInfo (String username, String info) throws SQLException {
		Long id = getUserIdByName(username);
		return userDao.setInfo(id, "“" + info + "”");
	}
	
}
