package com.save.brbserver.service.impl;

import com.save.brbserver.dao.IpDao;
import com.save.brbserver.entity.ItHouseIP;
import com.save.brbserver.service.ItHouseIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 20:49
 **/

@Service
public class ItHouseIpServiceImpl implements ItHouseIpService {
	
	@Autowired
	private IpDao ipDao;
	
	@Override
	public List<ItHouseIP> selectAllIPsOfCurrentPrefecture (String prefecture) throws SQLException {
		int typeId = ipDao.selectIpPrefecture(prefecture);
		return ipDao.selectAllIPsOfCurrentPrefecture(typeId);
	}
	
	@Override
	public List<ItHouseIP> selectIPByName (String name) throws SQLException {
		return ipDao.selectOneIPByName(name);
	}
	
	
}
