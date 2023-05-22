package com.save.brbserver.dao;

import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.Date;

/**
 * @Author:Zzs
 * @Description: 获取上次的时间，判断是否是连续登录，一天之内只能登录一次
 * @DateTime: 2023/5/21 12:41
 **/

@Mapper
public interface BootSignInDao {
	
	@Select ("select last_sign_in_time from boot_user_sign_in where user_id=#{u} and boot_id=#{b};")
	Date getLastTime (@Param ("u") Long userId, @Param ("b") Integer signId) throws SQLException;
	
	//如果签到时间是对的，就把连续签到的天数+1，否则置为1
	@Update ("update boot_user_sign_in set all_sign_in_count=all_sign_in_count+1, has_signed_up=has_signed_up+1, last_sign_in_time=now() " +
			"where user_id=#{u} and boot_id=#{b};")
	void signInContinuous (@Param ("u") Long userId, @Param ("b") Integer signId) throws SQLException;
	
	@Update ("update boot_user_sign_in set all_sign_in_count=all_sign_in_count+1, has_signed_up=1, last_sign_in_time=now() " +
			"where user_id=#{u} and boot_id=#{b};")
	void signInDisContinuous (@Param ("u") Long userId, @Param ("b") Integer signId) throws SQLException;
	
	@Select ("select has_signed_up from boot_user_sign_in where user_id=#{u} and boot_id=#{b};")
	Integer getSignInCount (@Param ("u") Long userId, @Param ("b") Integer signId) throws SQLException;
	
	@Select ("select max_signed_up from boot_user_sign_in where user_id=#{u} and boot_id=#{b};")
	Integer getMaxSignInCount (@Param ("u") Long userId, @Param ("b") Integer signId) throws SQLException;
	
	@Select ("select all_sign_in_count from boot_user_sign_in where user_id=#{u} and boot_id=#{b};")
	Integer getTotalSignInCount (@Param ("u") Long userId, @Param ("b") Integer signId) throws SQLException;
	
	@Insert ("insert into boot_user_sign_in value(#{u}, #{b}, 1, 1, now(), 1);")
	boolean startSignIn (@Param ("u") Long userId, @Param ("b") Integer signId) throws SQLException;
	
}
