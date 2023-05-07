package com.save.brbserver.controller;

import com.save.brbserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @Author:Zzs
 * @Description: register, login, logout,
 *               activities<create, participate, sign in, delete, query>
 * @DateTime: 2023/4/29 19:07
 **/

@RestController
@RequestMapping (value = "/user")
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	
	@PostMapping (value = "/register")
	public boolean userRegister (@RequestParam ("username") String username, @RequestParam ("password") String password, @RequestParam ("email") String email) {
		
		try {
			return userServiceImpl.userRegister(username, password, email);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 用户如果注册过，并且登陆过，除了返回状态码，还要将用户的信息（包括头像）返回
	 */
	@PostMapping (value = "/login")
	public boolean userLogin (@RequestParam ("username") String usernameOrEmail, @RequestParam ("password") String password) {
		//与数据库的密文密码对比，同时访问登录状态，如果已经登录，请求拒绝
		return true;
	}
	
	@GetMapping (value = "/logout")
	public boolean logout (@RequestParam ("username") String username) {
		return true;
	}
	
	@PostMapping (value = "/change-pwd")
	public boolean changePwd (@RequestParam ("username") String username, @RequestParam ("oldPassword") String oldPassword, @RequestParam ("password") String newPassword) {
		return true;
	}
	
	
}
