package com.save.brbserver.dao;

import com.save.brbserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

/**
 * @Author:Zzs
 * @Description: Data Access Object
 *                  Provides user interface, complete simple user registration, login op and so on.
 * @DateTime: 2023/4/24 18:15
 **/

@Mapper
public interface UserDao {
	
	//@param中的字符串与select语句中大括号内的字符串等同
	//@DateTime: 2023/4/29 21:35 pass
	@Select("select * from users where username=#{username};")
	User getByUsername(@Param("username") String username);
	
//	@Insert("")
//	boolean postUserHeadSculpture(String path);
	
	@Insert("insert into users(username, email, password, register_time, last_login_time) values(#{username}, #{email}, #{password}, now(), now());")
	boolean userRegister (@Param("username")String username, @Param("password")String password, @Param("email")String email);
	
}
