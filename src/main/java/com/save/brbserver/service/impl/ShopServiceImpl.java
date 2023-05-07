package com.save.brbserver.service.impl;

import com.save.brbserver.dao.ShopDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.ItHouseIP;
import com.save.brbserver.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 20:49
 **/

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopDao shopDao;
	private UserDao userDao;
	
	@Override
	public List<ItHouseIP> selectAllIPsOfCurrentPrefecture (String prefecture) throws SQLException {
		int typeId = shopDao.selectIpPrefecture(prefecture);
		return shopDao.selectAllIPsOfCurrentPrefecture(typeId);
	}
	
	@Override
	public Set<ItHouseIP> selectIPByName (String name) throws SQLException {
		String[] names = name.split(" ");
		Set<ItHouseIP> set = new HashSet<>();
		for (String s : names) {
			List<ItHouseIP> list = shopDao.selectOneIPByName(s);
			set.addAll(list);
		}
		return set;
	}
	
	@Override
	public boolean favorite (String username, int id, int type) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		
		return false;
	}
	
	
}
