package com.save.brbserver.controller;

import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Zzs
 * @Description: Og
 * @DateTime: 2023/5/20 21:58
 **/

@RestController
@RequestMapping ("/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@PostMapping ("/joined")
	public ResponseEntity<?> getJoinedOg (@RequestParam ("username") String username) {
		try {
			List<?> list = organizationService.getJoinedOrganizations(username);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, list, "获取成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(ResponseEntity.FAILED, null, "获取失败");
	}
}
