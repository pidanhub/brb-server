package com.save.brbserver.service;

import com.save.brbserver.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author:Zzs
 * @Description: call dao, declaration functions
 * @DateTime: 2023/4/29 18:52
 **/

public interface UserService {

	User getByUsername(String username);
	boolean userRegister(String username, String password, String email);
	
}
