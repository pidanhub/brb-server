package com.save.brbserver.dao;

import com.save.brbserver.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/29 21:13
 **/

@SpringBootTest
@Slf4j
class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	@Test
	void getByUsername () {
		User test = userDao.getByUsername("test");
		assertNotNull(test);
		System.out.println(test.getRegisterTime());
	}
	
	@Test
	void userRegister () {
		boolean test1 = userDao.userRegister("test1", "idasbflbfkja;ngkjkrn;laklnlk", "xx@xxx.com");
		System.out.println(test1);
	}
}