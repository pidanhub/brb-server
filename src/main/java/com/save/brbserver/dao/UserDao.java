package com.save.brbserver.dao;

import com.save.brbserver.entity.User;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.Map;

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
	 */
	@Select ("select * from users where username=#{username} or email=#{username};")
	User getByUsername (@Param ("username") String username) throws SQLException;
	
	@Update ("update users set last_login_time=now(), is_logged_in=1 where username=#{username} or email=#{username};")
	void userLoginUpdateTime (@Param ("username") String username) throws SQLException;
	
	@Insert ("update users set head_sculpture_path=#{path} where username=#{username};")
	boolean postUserHeadSculpture (@Param ("username") String username, @Param ("path") String path) throws SQLException;
	
	@Insert ("insert into users(username, email, password, nickname, register_time, last_login_time) " +
			"values(#{username}, #{email}, #{password}, #{nickname}, now(), now());")
	boolean userRegister (@Param ("username") String username, @Param ("nickname") String nickname, @Param ("password") String password, @Param ("email") String email) throws SQLException;
	
	
	boolean userLogout (@Param ("username") String username) throws SQLException;
	
	@Select ("select user_id from users where username = #{username} or email=#{username};")
	Long getUserIdByName (@Param ("username") String username) throws SQLException;
	
	@Select ("select username,email,nickname,gender,introduction,address,head_sculpture_path,integral " +
			"from users where user_id=#{userId};")
	Map<String, Object> getSimpleUserInfo (@Param ("userId") Long userId) throws SQLException;
	
	@Update ("update users set integral = integral + 1 where user_id=#{userId}")
	void addIntegral (@Param ("userId") Long userId) throws SQLException;
	
	@Update ("update users set introduction=#{info} where user_id=#{id};")
	boolean setInfo (@Param ("id") Long id, @Param ("info") String info) throws SQLException;
	
	@Update ("update users set nickname=#{nickname} where user_id=#{id};")
	boolean setNickname (@Param ("id") Long id, @Param ("nickname") String newNickname) throws SQLException;
	
	@Select ("select count(*) from user_ip_favorite where user_id=#{userId};")
	Integer countUserFavorite (@Param ("userId") Long userId) throws SQLException;
	
	@Select ("select count(*) from moments where user_id=#{userId} GROUP BY user_id;")
	Integer countUserMoments (@Param ("userId") Long userId) throws SQLException;
	
}
