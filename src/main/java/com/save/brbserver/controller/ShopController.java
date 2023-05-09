package com.save.brbserver.controller;

import com.save.brbserver.config.ConstantFields;
import com.save.brbserver.entity.ResponseEntity;
import com.save.brbserver.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@PostMapping ("/get-ip")
	public ResponseEntity<?> getIpInCurrentPrefecture (@RequestParam ("prefecture") String prefecture) {
		try {
			prefecture = prefecture.replaceAll(" ", "");
			return new ResponseEntity<>(ResponseEntity.SUCCESS,
					shopService.selectAllIPsOfCurrentPrefecture(prefecture),
					"成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping ("/search-ip")
	@Transactional (rollbackFor = Exception.class)
	public ResponseEntity<?> searchByName (@RequestParam ("name") String goodName) {
		try {
			return new ResponseEntity<>(ResponseEntity.SUCCESS, shopService.selectIPByName(goodName), "获取成功");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ResponseEntity.FAILED, null, "发生未知错误");
		}
	}
	
	@PostMapping ("/add-delete-favorite")
	public ResponseEntity<?> operationOnFavorite (@RequestParam ("username") String username, @RequestParam ("id") Long id, @RequestParam ("type") int type) {
		switch (type) {
			case ConstantFields.FAVORITE_TYPE_ADD:
			
			case ConstantFields.FAVORITE_TYPE_DELETE:
				break;
		}
		return null;
	}
}
