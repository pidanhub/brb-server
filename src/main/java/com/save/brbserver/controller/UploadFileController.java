package com.save.brbserver.controller;

import com.save.brbserver.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Zzs
 * @Description: Store jpg/png/text file
 * @DateTime: 2023/5/1 18:30
 **/

@RestController
@RequestMapping("/file")
public class UploadFileController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping (value = "/sethead")
	public boolean postUserHeadSculpture(@Param ("username")String username) {
		//TODO file operations
		return userService.postUserHeadSculpture(username);
	}
	
}
