package com.save.brbserver.service.impl;

import com.save.brbserver.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		System.out.println(userService.userLogin("test","bdkjsabf;nalfdknkngal"));
	}
	
	@Test
	void userRegister () {
		boolean test1 = userService.userRegister("test1", "idasbflbfkja;ngkjkrn;laklnlk", "yy@xxx.com");
		System.out.println(test1);
	}
}