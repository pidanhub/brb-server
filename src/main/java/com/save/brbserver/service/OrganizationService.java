package com.save.brbserver.service;

import com.save.brbserver.entity.Organization;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/20 21:50
 **/
public interface OrganizationService {
	
	List<Organization> getJoinedOrganizations (String username) throws SQLException;
	
}
