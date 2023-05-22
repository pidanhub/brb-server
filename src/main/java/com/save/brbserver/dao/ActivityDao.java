package com.save.brbserver.dao;

import com.save.brbserver.entity.Activity;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/30 15:27
 **/

@Mapper
public interface ActivityDao {
	
	@Insert ("insert into activities(`name`, belongs_to, activ_info, starttime, activ_location) values(#{name},#{belongsTo},#{activInfo},#{startTime},#{activLocation});")
	@Options (useGeneratedKeys = true, keyProperty = "activId", keyColumn = "activ_id")
	Long addActivity (Activity activity) throws SQLException;

//	@Select ("")
//	Long getActivityId () throws SQLException;
	
	@Select ("select activ_id,name,activ_info,starttime,activ_location,is_end,o_name as organizationName from activities,organizations " +
			"where activities.belongs_to=organizations.o_id;")
	List<Activity> getAllActivities () throws SQLException;
	
	@Insert ("insert into user_activs (#{u}, #{a}, 0);")
	void oneSignINActivity (@Param ("u") Long uId, @Param ("a") Long aId) throws SQLException;
	
	@Select ("select activ_id,name,activ_info,starttime,activ_location,is_end,o_name as organizationName from activities,organizations " +
			"where activ_id in (select a_id from user_activs where u_id=#{userId}) and activities.belongs_to=organizations.o_id;")
	List<Activity> getALLActivitiesUserHadJoined (@Param ("userId") Long userId) throws SQLException;
	
	@Select ("select activ_id,name,activ_info,starttime,activ_location,is_end,o_name as organizationName from activities,organizations " +
			"where activ_id in (select a_id from user_activs where u_id=#{userId} and is_sign_in=1) and activities.belongs_to=organizations.o_id;")
	List<Activity> getThoseActivitiesUserHadJoinedAndSignedIn (@Param ("userId") Long userId) throws SQLException;
	
	@Insert ("insert into user_activs values(#{userId}, #{activityId}, 0);")
	boolean signUpActivity (@Param ("userId") Long userId, @Param ("activityId") Long activityId) throws SQLException;
	
	@Update ("update user_activs set is_sign_in = 1 where u_id=#{userId} and a_id=#{activityId};")
	boolean signInActivity (@Param ("userId") Long userId, @Param ("activityId") Long activityId) throws SQLException;
	
	@Select ("select activ_id from activities,organizations " +
			"where activ_id in (select a_id from user_activs where u_id=#{userId}) and activities.belongs_to=organizations.o_id;")
	Set<Long> getALLActivitiesUserHadJoinedId (@Param ("userId") Long userId) throws SQLException;
	
	@Select ("select activ_id from activities,organizations " +
			"where activ_id in (select a_id from user_activs where u_id=#{userId} and is_sign_in=1) and activities.belongs_to=organizations.o_id;")
	Set<Long> getThoseActivitiesUserHadJoinedAndSignedInId (@Param ("userId") Long userId) throws SQLException;
	
	@Update ("update activities set is_end = 1 where activ_id in (${activityId}) and is_end = 0;")
	void setEnd (@Param ("activityId") String activityId) throws SQLException;
	
	@Select ("select * from activities where activ_id in (select a_id from user_activs where u_id=#{userId}) and is_end=1;")
	HashSet<Activity> getThoseActivitiesUserHadJoinedAndIsEnd (@Param ("userId") Long userId) throws SQLException;
}
