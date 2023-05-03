package com.save.brbserver.dao;

import com.save.brbserver.entity.Activities;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/30 15:27
 **/

@Mapper
public interface ActivityDao {
	
	@Insert ("insert into activities(name, belongs_to, activ_info, starttime, activ_location) values(#{name}, #{belongsToUserId},#{info},#{startTime},#{location});")
	boolean addActivity (@Param ("name") String name, @Param ("belongsToUserId") Long belongsToUserId, @Param ("info") String info,
	                     @Param ("startTime") Timestamp startTime, @Param ("location") String location);
	
	boolean getActivityByName ();
	
	boolean getAllActivities ();
	
	
	boolean oneSignINActivity (String username, String aName, Timestamp startTime, String location);
	
	@Select ("select * from activities where activ_id in (select a_id from user_activs where u_id=#{userId});")
	List<Activities> getALLActivitiesUserHadJoined (@Param ("userId") Long userId);
	
	@Select ("select * from activities where activ_id in (select a_id from user_activs where u_id=#{userId} and is_sign_in=1);")
	List<Activities> getThoseActivitiesUserHadJoinedAndSignedIn (@Param ("userId") Long userId);
	
}
