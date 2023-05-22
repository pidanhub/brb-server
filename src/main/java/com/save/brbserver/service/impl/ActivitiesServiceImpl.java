package com.save.brbserver.service.impl;

import com.save.brbserver.dao.ActivityDao;
import com.save.brbserver.dao.OrganizationsDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.Activity;
import com.save.brbserver.service.ActivitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
	@Autowired
	private OrganizationsDao organizationsDao;
	
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
	public Long addActivity (String username, String organizationName, String name, String info, Timestamp startTime, String location) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		Long organizationId = organizationsDao.getOrganizationIdByName(organizationName);
		Activity activity = Activity.builder()
				.name(name)
				.belongsTo(organizationId)
				.activInfo(info)
				.activLocation(location)
				.startTime(startTime)
				.isSignedUp(true)
				.isSigndedIn(false)
				.build();
		activityDao.addActivity(activity);
		activityDao.oneSignINActivity(userId, activity.getActivId()); //同时参加
		userDao.addIntegral(userId);
		return activity.getActivId();
	}
	
	@Override
	public boolean signUpActivity (String username, Long aId) throws SQLException {
		return activityDao.signUpActivity(userDao.getUserIdByName(username), aId);
	}
	
	@Override
	public boolean signInActivity (String username, Long aId) throws SQLException {
		return activityDao.signInActivity(userDao.getUserIdByName(username), aId);
	}
	
	@Override
	public List<Activity> getALLActivities (String username) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		List<Activity> list = activityDao.getAllActivities();
		Set<Long> joined = activityDao.getALLActivitiesUserHadJoinedId(userId);
		Set<Long> signed = activityDao.getThoseActivitiesUserHadJoinedAndSignedInId(userId);
		for (Activity a : list) {
			Long id = a.getActivId();
			a.setIsSignedUp(joined.contains(id));
			a.setIsSigndedIn(signed.contains(id));
		}
		return list;
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public List<Activity> getThoseActivitiesUserHadJoinedAndIsEnd (String username) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		Set<Activity> end = activityDao.getThoseActivitiesUserHadJoinedAndIsEnd(userId);
		List<Activity> list = activityDao.getALLActivitiesUserHadJoined(userId);
		List<Long> toSet = new ArrayList<>();
		Date now = new Date();
		for (Activity a : list) {
			if (end.contains(a))
				continue;
			Date date = a.getStartTime();
			if (now.before(date)) {
				toSet.add(a.getActivId());
				list.remove(a);
				continue;
			}
			long diff = Math.abs(date.getTime() - now.getTime()) / (1000 * 3600 * 24) / 7; //几周，以周为单位的长度
			if (diff < 1) { // 未超过一周，不在已结束列表内
				list.remove(a);
			}
			else
				toSet.add(a.getActivId());
		}
		if (toSet.size() != 0) {
			new Thread(() -> {
				StringBuilder sb = new StringBuilder(toSet.get(0).toString());
				for (Long i : toSet)
					sb.append(",").append(i.toString());
				try {
					log.info(toSet.toString());
					activityDao.setEnd(sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
		return list;
	}
	
}
