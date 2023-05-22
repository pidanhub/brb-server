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
import java.sql.SQLIntegrityConstraintViolationException;
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
	
	@PostMapping ("/get-joined")
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
			//同时主人也会参加活动
			Long id = activitiesService.addActivity(username, name, info, Timestamp.valueOf(startTime), location);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, id, "成功创建活动");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, false, "创建活动失败");
		}
	}
	
	@PostMapping ("/sign-up")
	public ResponseEntity<?> signUp (@RequestParam ("username") String username, @RequestParam ("activityId") Long activId) {
		try {
			if (activitiesService.signUpActivity(username, activId))
				return new ResponseEntity<>(ResponseEntity.SUCCESS, true, "报名成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			if (cause instanceof SQLIntegrityConstraintViolationException)
				return new ResponseEntity<>(ResponseEntity.UNIQUE_FIELD_ALREADY_EXIST, null, "已经报名过，不要重复报名");
		}
		return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误，报名失败");
	}
	
	@PostMapping ("/sign-up")
	public ResponseEntity<?> signIn (@RequestParam ("username") String username, @RequestParam ("activityId") Long activId) {
		try {
			if (activitiesService.signInActivity(username, activId))
				return new ResponseEntity<>(ResponseEntity.SUCCESS, true, "签到成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			if (cause instanceof SQLIntegrityConstraintViolationException)
				return new ResponseEntity<>(ResponseEntity.UNIQUE_FIELD_ALREADY_EXIST, null, "已经签到过，不要重复签到");
		}
		return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误，签到失败");
	}
	
}
