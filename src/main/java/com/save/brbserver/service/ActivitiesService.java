package com.save.brbserver.service;

import com.save.brbserver.entity.Activities;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:Zzs
 * @Description: declare functions
 * @DateTime: 2023/5/1 19:24
 **/

public interface ActivitiesService {
	List<Activities> getThoseActivitiesUserHadJoinedAndSignedIn (String username) throws SQLException;
	
	List<Activities> getALLActivitiesUserHadJoined (String username) throws SQLException;
	
	boolean addActivity (String username, String name, String info, Timestamp startTime, String location) throws SQLException;
	
}
