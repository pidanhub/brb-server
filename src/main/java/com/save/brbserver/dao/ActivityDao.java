package com.save.brbserver.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/30 15:27
 **/

@Mapper
public interface ActivityDao {
	
	@Insert ("insert into activities(belongs_to, activ_info, starttime, activ_location) values(#{belongsToUserId},#{info},#{startTime},#{activ_location});")
	boolean addActivity (@Param ("belongsToUserId") Long belongsToUserId, @Param ("info") String info,
	                     @Param ("startTime") Date startTime, @Param ("location") String location);
	
	boolean getActivity();
}
