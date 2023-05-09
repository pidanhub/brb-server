package com.save.brbserver.controller;

import com.save.brbserver.entity.Activity;
import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/30 15:07
 **/

@RestController
@RequestMapping ("/activity")
public class ActivitiesController {
	
	@Autowired
	private ActivitiesService activitiesService;
	
	@PostMapping ("/get-sign-in")
	public ResponseEntity<?> getThoseActivitiesUserHadJoinedAndSignedIn (@RequestParam ("username") String username) {
		try {
			List<Activity> thoseActivitiesUserHadJoinedAndSignedIn =
					activitiesService.getThoseActivitiesUserHadJoinedAndSignedIn(username);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, thoseActivitiesUserHadJoinedAndSignedIn, "获取成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "刷新重试");
		}
	}
	
	@PostMapping ("/get-all")
	public ResponseEntity<?> getALLActivitiesUserHadJoined (@RequestParam ("username") String username) {
		try {
			List<Activity> getALLActivitiesUserHadJoined =
					activitiesService.getALLActivitiesUserHadJoined(username);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, getALLActivitiesUserHadJoined, "获取成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "刷新重试");
		}
	}
	
	@PostMapping ("/add")
	public ResponseEntity<?> addActivity (@RequestParam ("username") String username, @RequestParam ("name") String name, @RequestParam ("info") String info,
	                                      @RequestParam ("starttime") String startTime, @RequestParam ("location") String location) {
		try {
			Long id = activitiesService.addActivity(username, name, info, Timestamp.valueOf(startTime), location);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, id, "成功创建活动");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, false, "创建活动失败");
		}
	}
	
}
