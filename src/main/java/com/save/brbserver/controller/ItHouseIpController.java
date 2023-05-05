package com.save.brbserver.controller;

import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.ItHouseIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 20:57
 **/

@RestController
@RequestMapping ("/shop")
public class ItHouseIpController {
	
	@Autowired
	private ItHouseIpService itHouseIpService;
	
	@PostMapping ("/get-ip")
	public ResponseEntity<?> getIpInCurrentPrefecture (@RequestParam ("prefecture") String prefecture) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS,
					itHouseIpService.selectAllIPsOfCurrentPrefecture(prefecture),
					"成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping ("/search-ip")
	public ResponseEntity<?> searchByName (@RequestParam ("name") String name) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS, itHouseIpService.selectIPByName(name), "获取成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "发生未知错误");
		}
	}
	
}
