package com.save.brbserver.service.impl;

import com.save.brbserver.entity.User;
import com.save.brbserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/29 21:39
 **/

@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	void getByUsername () {
		User user = userService.getByUsername("test1");
		assertNotNull(user);
	}
	
	@Test
	void userRegister () {
		boolean test1 = userService.userRegister("test1", "idasbflbfkja;ngkjkrn;laklnlk", "yy@xxx.com");
		System.out.println(test1);
	}
}