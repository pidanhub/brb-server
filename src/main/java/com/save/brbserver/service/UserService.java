package com.save.brbserver.service;

import com.save.brbserver.customexception.FormatException;
import com.save.brbserver.customexception.MySecurityException;

import java.sql.SQLException;
import java.util.Map;

/**
 * @Author:Zzs
 * @Description: call dao, declaration functions
 * @DateTime: 2023/4/29 18:52
 **/

public interface UserService {
	
	Map<String, String> userLogin (String username, String password) throws SQLException;
	
	boolean userRegister (String username, String nickname, String password, String email) throws SQLException, FormatException;
	
	boolean postUserHeadSculpture (String username, String path) throws SQLException;
	
	boolean changePassword (String username, String password) throws SQLException;
	
	Long getUserIdByName (String username) throws SQLException;
	
	Map<String, Object> getSimpleUserInfo (String username) throws SQLException;
	
	boolean setInfo (String username, String info) throws SQLException;
	
	boolean setNickname (String username, String newNickname) throws SQLException;
	
	boolean signInBoot (String username, Integer bootId) throws SQLException, MySecurityException;
}
