package com.save.brbserver.controller;

import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.MomentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

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
	
	@PostMapping ("/post")
	public ResponseEntity<?> postMoments (@RequestParam ("username") String username, @RequestParam ("text") String text) {
		try {
			Long id = momentsService.postMoments(username, text);
			return new ResponseEntity<>(ResponseEntity.SUCCESS, id, "发布成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
		}
	}
	
	// TODO 以下三个要设计参数
	@PostMapping ("/query")
	public ResponseEntity<?> queryMoments (@RequestParam ("username") String username, @RequestParam ("") String text) {
		
		return null;
	}
	
	@PostMapping ("/delete")
	public ResponseEntity<?> deleteMoments (@RequestParam ("username") String username, @RequestParam ("") String text) {
		return null;
	}
	
	@PostMapping ("/get")
	public ResponseEntity<?> getMoments (@RequestParam ("username") String username) {
		
		return null;
	}
	
}
