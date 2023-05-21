package com.save.brbserver.service.impl;

import com.save.brbserver.dao.OrganizationsDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.Organization;
import com.save.brbserver.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/20 21:55
 **/

@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrganizationsDao organizationsDao;
	
	@Override
	public List<Organization> getJoinedOrganizations (String username) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		return organizationsDao.getJoinedOg(userId);
	}
}
