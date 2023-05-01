package com.save.brbserver.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/30 11:33
 **/

@SpringBootTest
class SecurityUserServiceImplTest {
	
	@Autowired
	private SecurityUserServiceImpl securityUserService;
	@Test
	void loadUserByUsername () {
		UserDetails userDetails = securityUserService.loadUserByUsername("test");
		// TODO 时间戳格式化有误
		System.out.println(userDetails);
	}
}