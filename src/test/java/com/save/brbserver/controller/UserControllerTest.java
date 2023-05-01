package com.save.brbserver.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/1 11:12
 **/

@SpringBootTest
class UserControllerTest {
	
	@Autowired
	UserController userController;
	
	@Test
	void userRegister () {
		boolean test1 = userController.userRegister("test1", "idasbflbfkja;ngkjkrn;laklnlk", "xx@xxx.com");
		System.out.println(test1);
	}
}