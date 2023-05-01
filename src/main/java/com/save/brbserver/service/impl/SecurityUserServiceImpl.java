package com.save.brbserver.service.impl;

import com.save.brbserver.entity.User;
import com.save.brbserver.service.UserService;
import com.save.brbserver.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: Zzs
 * @Description: create security user
 * @DateTime: 2023/4/30 11:24
 **/

@Service
@Slf4j
public class SecurityUserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		User user = userService.getByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("There isn't a user named " + username+".");
		}
		
		return new SecurityUser(user);
	}
	
}
