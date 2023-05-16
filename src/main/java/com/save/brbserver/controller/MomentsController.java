package com.save.brbserver.controller;

import com.save.brbserver.entity.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Zzs
 * @Description: User posts or deletes moments, query others moments, or just display
 * @DateTime: 2023/5/15 8:47
 **/

@RestController
@RequestMapping ("/moments")
public class MomentsController {
	
	@PostMapping ("/post")
	public ResponseEntity<?> postMoments (@RequestParam ("username") String username, @RequestParam ("text") String text) {
		
		return null;
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
	public ResponseEntity<?> getMoments (@RequestParam ("username") String username, @RequestParam ("") String text) {
		
		return null;
	}
	
}
