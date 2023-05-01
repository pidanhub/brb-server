package com.save.brbserver.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/30 15:35
 **/

@SpringBootTest
class ActivityDaoTest {
	
	@Autowired
	private ActivityDao activityDao;
	@Test
	void addActivity () {
		boolean is = activityDao.addActivity(1L,"测试活动",new Date(), "天津大学北洋园校区");
		System.out.println(is);
	}
}