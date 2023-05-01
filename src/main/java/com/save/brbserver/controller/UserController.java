package com.save.brbserver.controller;

import com.save.brbserver.config.ConstantFields;
import com.save.brbserver.entity.User;
import com.save.brbserver.service.impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author:Zzs
 * @Description: register, login, logout, activities<create, participate, sign in, delete, query>
 * @DateTime: 2023/4/29 19:07
 **/

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping(value = "/register")
	public boolean userRegister(@Param("username")String username, @Param("password")String password, @Param("email")String email) {
		return userServiceImpl.userRegister(username, password, email);
	}
	
	/**
	 * 用户如果注册过，并且登陆过，除了返回状态码，还要将用户的信息（包括头像）返回
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping(value = "/login")
	public boolean userLogin(@Param("username")String username, @Param("password")String password) {
		//与数据库的密文密码对比，同时访问登录状态，如果已经登录，请求拒绝
		return true;
	}
	
//	@GetMapping(value = "/logout")
//	public boolean logout(@Param("username")String username) {
//
//		return true;
//	}
//
//	@PostMapping(value = "/sethead")
//	public boolean postUserHeadSculpture(@Param("username")String username) {
//		Date date = new Date();
//		return true;
//	}
//
//	@PostMapping(value = "/changepwd")
//	public boolean changePwd(@Param("username")String username, @Param("password")String password) {
//		return true;
//	}
//
	
}
