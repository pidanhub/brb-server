package com.save.brbserver.service.impl;

import com.save.brbserver.dao.ActivityDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.Activity;
import com.save.brbserver.service.ActivitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Activity> getThoseActivitiesUserHadJoinedAndSignedIn (String username) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		return activityDao.getThoseActivitiesUserHadJoinedAndSignedIn(userId);
	}
	
	@Override
	public List<Activity> getALLActivitiesUserHadJoined (String username) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		return activityDao.getALLActivitiesUserHadJoined(userId);
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public Long addActivity (String username, String name, String info, Timestamp startTime, String location) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		Activity activity = Activity.builder()
				.name(name)
				.belongsTo(userId)
				.activInfo(info)
				.activLocation(location)
				.startTime(startTime)
				.build();
		activityDao.addActivity(activity);
		activityDao.oneSignINActivity(userId, activity.getActivId()); //同时参加
		userDao.addIntegral(userId);
		return activity.getActivId();
	}
}
