package com.save.brbserver.service;

/**
 * @Author:Zzs
 * @Description: call dao, declaration functions
 * @DateTime: 2023/4/29 18:52
 **/

public interface UserService {
	
	boolean userLogin (String username, String password);
	boolean userRegister (String username, String password, String email);
	boolean postUserHeadSculpture(String username);
	boolean changePassword(String username, String password);
	
}
