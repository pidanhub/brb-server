package com.save.brbserver.service.impl;

import com.save.brbserver.config.ConstantFields;
import com.save.brbserver.dao.ShopDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.ItHouseIP;
import com.save.brbserver.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public List<ItHouseIP> selectAllIPsOfCurrentPrefecture (String username, String prefecture) throws SQLException {
		int typeId = shopDao.selectIpPrefecture(prefecture);
		List<ItHouseIP> list = shopDao.selectAllIPsOfCurrentPrefecture(typeId);
		if (list.size() != 0) {
			Set<Integer> set = shopDao.findUserFavorites(userDao.getUserIdByName(username));
			if (set != null) {
				for (ItHouseIP ip : list)
					ip.setIsFavorite(set.contains(ip.getIpId()));
			}
			else {
				for (ItHouseIP ip : list)
					ip.setIsFavorite(false);
			}
			
		}
		return list;
	}
	
	@Override
	public Set<ItHouseIP> selectIPByName (String username, String name) throws SQLException {
		String[] names = name.split(" ");
		Set<ItHouseIP> set = new HashSet<>();
		for (String s : names) {
			List<ItHouseIP> list = shopDao.selectOneIPByName(s);
			set.addAll(list);
		}
		if (set.size() != 0) {
			Set<Integer> favorites = shopDao.findUserFavorites(userDao.getUserIdByName(username));
			if (favorites != null) {
				for (ItHouseIP ip : set)
					ip.setIsFavorite(favorites.contains(ip.getIpId()));
			}
			else
				for (ItHouseIP ip : set)
					ip.setIsFavorite(false);
			
		}
		return set;
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public boolean favorite (String username, int id, int type) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		switch (type) {
			case ConstantFields.FAVORITE_TYPE_ADD:
				return shopDao.addFavorite(userId, id);
			case ConstantFields.FAVORITE_TYPE_DELETE:
				return shopDao.deleteFavorite(userId, id);
		}
		return false;
	}
	
	@Override
	public List<ItHouseIP> getUserFavorite (String username) throws SQLException {
		List<ItHouseIP> list = shopDao.getUserFavorite(userDao.getUserIdByName(username));
		for (ItHouseIP ih : list)
			ih.setIsFavorite(true);
		return list;
	}
	
}
