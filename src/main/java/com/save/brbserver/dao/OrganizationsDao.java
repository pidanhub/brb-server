package com.save.brbserver.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 15:44
 **/

@Mapper
public interface OrganizationsDao {
	
	@Insert ("insert into organizations(o_name, who_create, create_time, introduction) values(#{name}, #{userId}, now(), #{introduction});")
	boolean addOrganization (@Param ("name") String name, @Param ("userId") Long userId, @Param ("introduction") String introduction);
	
	@Insert ("insert into o_u(o_id, u_id, partin_time) values(#{organizationId}, #{userId}, now());")
	boolean participateOrganizations (@Param ("userId") Long userId, @Param ("organizationsId") Long organizationsId);
	
	
}
