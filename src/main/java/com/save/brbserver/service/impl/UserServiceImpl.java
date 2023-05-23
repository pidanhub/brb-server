package com.save.brbserver.service.impl;

import com.save.brbserver.customexception.FormatException;
import com.save.brbserver.customexception.MySecurityException;
import com.save.brbserver.dao.BootSignInDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.User;
import com.save.brbserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
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
	@Autowired
	private BootSignInDao bootSignInDao;
	
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
//		log.info(encodePassword);
		if (encodePassword.equals(up)) {
			return map;
		}
		else
			return null;
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	@SuppressWarnings ("all")
	public boolean userRegister (String username, String nickname, String password, String email) throws SQLException, FormatException {
		String encodePassword = DigestUtils.md5Hex(password);
		return userDao.userRegister(username, nickname, encodePassword, email);
	}
	
	@Override
	public void postUserHeadSculpture (String username, String path) throws SQLException {
		userDao.postUserHeadSculpture(username, path);
	}
	
	@Override
	public boolean changePassword (String username, String password, String newPwd) throws SQLException, MySecurityException {
		User user = userDao.getByUsername(username);
		if (user == null)
			return false;
		String encodeOldPassword = DigestUtils.md5Hex(password);
		if (!encodeOldPassword.equals(user.getPassword())) {
			throw new MySecurityException();
		}
		String encodeNewPassword = DigestUtils.md5Hex(newPwd);
		return userDao.changePassword(user.getUserId(), encodeNewPassword);
	}
	
	@Override
	public Long getUserIdByName (String username) throws SQLException {
		return userDao.getUserIdByName(username);
	}
	
	@Override
	public Map<String, Object> getSimpleUserInfo (String username) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		Map<String, Object> map = userDao.getSimpleUserInfo(userId);
		map.put("momentsCount", userDao.countUserMoments(userId));
		Integer countUserFavorite = userDao.countUserFavorite(userId);
		map.put("favoriteCount", countUserFavorite == null ? 0 : countUserFavorite);
		Integer signInCount = bootSignInDao.getSignInCount(userId, 1);
		map.put("signInCount", signInCount == null ? 0 : signInCount);
		Integer signInMaxCount = bootSignInDao.getMaxSignInCount(userId, 1);
		map.put("signInMaxCount", signInMaxCount == null ? 0 : signInMaxCount);
		Integer totalSignInCount = bootSignInDao.getTotalSignInCount(userId, 1);
		map.put("totalSignInCount", totalSignInCount == null ? 0 : totalSignInCount);
		
		return map;
	}
	
	@Override
	public boolean setInfo (String username, String info) throws SQLException {
		Long id = getUserIdByName(username);
		return userDao.setInfo(id, "“" + info + "”");
	}
	
	@Override
	public boolean setNickname (String username, String newNickname) throws SQLException {
		return userDao.setNickname(userDao.getUserIdByName(username), newNickname);
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public boolean signInBoot (String username, Integer bootId) throws SQLException, MySecurityException {
		Long userId = userDao.getUserIdByName(username);
		Date date = bootSignInDao.getLastTime(userId, bootId);
		if (date == null) {
			return bootSignInDao.startSignIn(userId, bootId);
		}
		Date now = new Date();
		long time = Math.abs(now.getTime() - date.getTime()) / (24 * 60 * 60 * 1000L);
		if (now.before(date))
			throw new MySecurityException();
		boolean sameDay = DateUtils.isSameDay(date, now);
		if (sameDay) {
			// 是同一天，所以不给签到
			return false;
		}
		else if (time <= 1) {
			// 是两个日期，如果考虑最长，即第一天的头和第二天的尾，时间应该相差将近两天，不可能到二，time一定小于等于1，可以签到且为连续签到
			bootSignInDao.signInContinuous(userId, bootId);
		}
		else {
			// 超出了，已经不连续了，可以签到，但是需要清空为1
			bootSignInDao.signInDisContinuous(userId, bootId);
		}
		userDao.addIntegral(userId);
		return true;
	}
	
	@Override
	public boolean logout (String username) throws SQLException {
		return userDao.userLogout(userDao.getUserIdByName(username));
	}
	
}
