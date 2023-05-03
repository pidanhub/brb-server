package com.save.brbserver.service;

import com.save.brbserver.entity.Activity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:Zzs
 * @Description: declare functions
 * @DateTime: 2023/5/1 19:24
 **/

public interface ActivitiesService {
	List<Activity> getThoseActivitiesUserHadJoinedAndSignedIn (String username) throws SQLException;
	
	List<Activity> getALLActivitiesUserHadJoined (String username) throws SQLException;
	
	Long addActivity (String username, String name, String info, Timestamp startTime, String location) throws SQLException;
	
}
