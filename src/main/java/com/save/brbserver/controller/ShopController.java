package com.save.brbserver.controller;

import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 20:57
 **/

@RestController
@RequestMapping ("/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@PostMapping ("/get-ip")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<?> getIpInCurrentPrefecture (@RequestParam ("username") String username, @RequestParam ("prefecture") String prefecture) {
		try {
			prefecture = prefecture.replaceAll("[ ]", "");
			return new ResponseEntity<>(ResponseEntity.SUCCESS,
					shopService.selectAllIPsOfCurrentPrefecture(username, prefecture),
					"成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping ("/search-ip")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<?> searchByName (@RequestParam ("username") String username, @RequestParam ("name") String goodName) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS, shopService.selectIPByName(username, goodName), "获取成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "发生未知错误");
		}
	}
	
	@PostMapping ("/add-delete-favorite")
	public ResponseEntity<?> operationOnFavorite (@RequestParam ("username") String username,
	                                              @RequestParam ("id") int id, @RequestParam ("type") int type) {
		try {
			boolean isOK = shopService.favorite(username, id, type);
			if (isOK)
				return new ResponseEntity<>(ResponseEntity.SUCCESS, null, "成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Throwable cause = e.getCause();
			if (cause instanceof SQLIntegrityConstraintViolationException)
				return new ResponseEntity<>(ResponseEntity.UNIQUE_FIELD_ALREADY_EXIST, false, "拒绝重复收藏或取消收藏");
			return new ResponseEntity<>(ResponseEntity.FAILED, false, "未知错误");
		}
		return new ResponseEntity<>(ResponseEntity.FAILED, null, "失败");
	}
	
	@PostMapping ("/get-favorite")
	public ResponseEntity<?> getUserFavorite (@RequestParam ("username") String username) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS, shopService.getUserFavorite(username), "获取成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(ResponseEntity.FAILED, null, "未知错误");
	}
}
