package com.save.brbserver.dao;

import com.save.brbserver.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * <p> html test </p>
 *
 * @Author:Zzs
 * @Description: Data Access Object
 * Provides user interface, complete simple user registration, login op and so on.
 * @DateTime: 2023/4/24 18:15
 **/

@Mapper
public interface UserDao {
	
	/**
	 * @param username 中的字符串与select语句中大括号内的字符串等同
	 *                 关于登录应有两项操作，首先从该数据库中拿出用户的信息，然后更新用户的登录时间
	 * @return
	 */
	@Select ("select * from users where username=#{username};")
	User getByUsername (@Param ("username") String username);
	@Update("update users set last_login_time=now(), is_logged_in=1 where username=#{username};")
	boolean userLoginUpdateTime (@Param("username") String username);
	
	@Insert ("update users set head_sculpture_path=/images/#{path} where username=#{username};")
	boolean postUserHeadSculpture (@Param("username") String username, @Param("path") String path);
	
	@Insert ("insert into users(username, email, password, register_time, last_login_time) values(#{username}, #{email}, #{password}, now(), now());")
	boolean userRegister (@Param ("username") String username, @Param ("password") String password, @Param ("email") String email);
	
	
	boolean userLogout(@Param("username") String username);
	
	
	
}
