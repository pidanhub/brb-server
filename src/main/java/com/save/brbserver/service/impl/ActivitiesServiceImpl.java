package com.save.brbserver.service.impl;

import com.save.brbserver.dao.ActivityDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.Activities;
import com.save.brbserver.service.ActivitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/1 19:26
 **/

@Service
@Slf4j
public class ActivitiesServiceImpl implements ActivitiesService {
	
	@Autowired
	private ActivityDao activityDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<Activities> getThoseActivitiesUserHadJoinedAndSignedIn (String username) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		return activityDao.getThoseActivitiesUserHadJoinedAndSignedIn(userId);
	}
	
	@Override
	public List<Activities> getALLActivitiesUserHadJoined (String username) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		return activityDao.getALLActivitiesUserHadJoined(userId);
	}
	
	@Override
	public boolean addActivity (String username, String name, String info, Timestamp startTime, String location) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		return activityDao.addActivity(name, userId, info, startTime, location);
	}
}
