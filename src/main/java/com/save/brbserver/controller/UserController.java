package com.save.brbserver.controller;

import com.save.brbserver.customexception.FormatException;
import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.entity.TokenEntity;
import com.save.brbserver.service.UserService;
import com.save.brbserver.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

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
	private UserService userService;
	
	@PostMapping (value = "/register")
	public ResponseEntity<?> userRegister (@RequestParam ("username") String username, @RequestParam ("nickname") String nickname, @RequestParam ("password") String password, @RequestParam ("email") String email,
	                                       @RequestParam ("verify-code") String verificationCode) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS, userService.userRegister(username, nickname, password, email), "注册成功");
		} catch (FormatException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FORMAT_NOT_RIGHT, null, "邮箱格式不正确");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
		} catch (Exception e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			if (cause instanceof SQLIntegrityConstraintViolationException)
				return new ResponseEntity<>(ResponseEntity.UNIQUE_FIELD_ALREADY_EXIST, null, "用户名或邮箱已被占用");
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "注册失败");
		}
	}
	
	/**
	 * 用户如果注册过，并且登陆过，除了返回状态码，还要将用户的信息（包括头像）返回
	 */
	@PostMapping (value = "/login")
	public ResponseEntity<?> userLogin (@RequestParam ("username") String usernameOrEmail, @RequestParam ("password") String password) {
		//与数据库的密文密码对比，同时访问登录状态，如果已经登录，请求拒绝
		try {
			Map<String, String> map = userService.userLogin(usernameOrEmail, password);
			if (map == null) {
				return new ResponseEntity<>(ResponseEntity.FAILED, null, "用户名或密码错误");
			}
			TokenEntity token = JWTUtils.getToken(map);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, token, "登陆成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
		}
	}
	
	@PostMapping (value = "/init")
	public ResponseEntity<?> init (@RequestParam ("username") String username) {
		try {
			Map<String, Object> map = userService.getSimpleUserInfo(username);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, map, "成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
		}
	}
	
	@GetMapping (value = "/logout")
	public boolean logout (@RequestParam ("username") String username) {
		return true;
	}
	
	@PostMapping (value = "/change-pwd")
	public boolean changePwd (@RequestParam ("username") String username, @RequestParam ("oldPassword") String oldPassword, @RequestParam ("password") String newPassword) {
		return true;
	}
	
	@PostMapping (value = "/set-info")
	public ResponseEntity<?> setInfo (@RequestParam ("username") String username, @RequestParam ("info") String info) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS, userService.setInfo(username, info), "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, false, "未知错误");
		}
	}
	
	@PostMapping (value = "/change-nickname")
	public ResponseEntity<?> changeNickname (@RequestParam ("username") String username, @RequestParam ("nickname") String newNickname) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS, userService.setNickname(username, newNickname), "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, false, "未知错误");
		}
	}
	
}
