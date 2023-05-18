package com.save.brbserver.controller;

import com.save.brbserver.customexception.MySecurityException;
import com.save.brbserver.entity.Moments;
import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.MomentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Zzs
 * @Description: User posts or deletes moments, query others moments, or just display
 * @DateTime: 2023/5/15 8:47
 **/

@RestController
@RequestMapping ("/moment")
public class MomentsController {
	
	@Autowired
	private MomentsService momentsService;
	
	@PostMapping ("/post") //done
	public ResponseEntity<?> postMoments (@RequestParam ("username") String username, @RequestParam ("text") String text) {
		try {
			Long id = momentsService.postMoments(username, text);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, id, "发布成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
		}
	}
	
	@PostMapping ("/detail")
	public ResponseEntity<?> momentDetails (@RequestParam ("username") String username, @RequestParam ("id") Long id) {
		try {
			Map<Integer, String> map = momentsService.details(id);
			if (map == null || map.size() == 0)
				map = new HashMap<>();
			return new ResponseEntity<>(ResponseEntity.SUCCESS, map, "获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "获取失败");
		}
	}
	
	@PostMapping ("/delete")
	public ResponseEntity<?> deleteMoments (@RequestParam ("username") String username, @RequestParam ("momentId") Long id) {
		try {
			if (momentsService.deleteMoment(username, id))
				return new ResponseEntity<>(ResponseEntity.SUCCESS, null, "删除成功");
			else
				return new ResponseEntity<>(ResponseEntity.FAILED, null, "删除失败");
		} catch (MySecurityException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.DANGEROUS, null, "不安全的请求");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(ResponseEntity.FAILED, null, "删除失败");
	}
	
	@PostMapping ("/get")
	public ResponseEntity<?> getMoments (@RequestParam ("username") String username, @RequestParam ("page") int page) {
		try {
			List<Moments> list = momentsService.getAllMoments(username, page);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, list, "获取成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "获取失败");
		}
	}
	
	@PostMapping ("/like")
	public ResponseEntity<?> likeMoment (@RequestParam ("username") String username, @RequestParam ("id") Long id) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS, momentsService.like(username, id), "点赞成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, false, "未知错误");
		} catch (Exception e) {
			Throwable cause = e.getCause();
			if (cause instanceof SQLIntegrityConstraintViolationException)
				return new ResponseEntity<>(ResponseEntity.UNIQUE_FIELD_ALREADY_EXIST, false, "已经点赞过");
			return new ResponseEntity<>(ResponseEntity.FAILED, false, "未知错误");
		}
	}
	
	@PostMapping ("/dislike")
	public ResponseEntity<?> dislikeMoment (@RequestParam ("username") String username, @RequestParam ("id") Long id) {
		try {
			if (momentsService.dislike(username, id))
				return new ResponseEntity<>(ResponseEntity.SUCCESS, true, "取消点赞成功");
			else
				return new ResponseEntity<>(ResponseEntity.FAILED, false, "重复取消");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, false, "未知错误");
		}
	}
	
}
