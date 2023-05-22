package com.save.brbserver.service;

import com.save.brbserver.entity.ItHouseIP;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @Author:Zzs
 * @Description: <p>Get all ips, I will tell the clients all the pictures' path, then the clients will ask me for the pictures. So there are two interfaces.</p>
 * @DateTime: 2023/5/2 20:45
 **/

public interface ShopService {
	
	List<ItHouseIP> selectAllIPsOfCurrentPrefecture (String username, String prefecture) throws SQLException;
	
	Set<ItHouseIP> selectIPByName (String username, String name) throws SQLException;
	
	boolean favorite (String username, int id, int type) throws SQLException;
	
	List<ItHouseIP> getUserFavorite (String username) throws SQLException;
	
}
