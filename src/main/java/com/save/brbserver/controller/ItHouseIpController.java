package com.save.brbserver.controller;

import com.save.brbserver.entity.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 20:57
 **/

@RestController
@RequestMapping ("/shop")
public class ItHouseIpController {
	
	@GetMapping ("/get-ip")
	public ResponseEntity<?> getIpInCurrentPrefecture (@RequestParam ("prefecture") String prefecture) {
		return null;
	}
}
