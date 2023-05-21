package com.save.brbserver.dao;

import com.save.brbserver.entity.Organization;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 15:44
 **/

@Mapper
public interface OrganizationsDao {
	
	@Insert ("insert into organizations(o_name, who_create, create_time, introduction) values(#{name}, #{userId}, now(), #{introduction});")
	boolean addOrganization (@Param ("name") String name, @Param ("userId") Long userId, @Param ("introduction") String introduction);
	
	@Insert ("insert into user_organization(o_id, u_id, part_in_time) values(#{organizationId}, #{userId}, now());")
	List<Organization> participateOrganizations (@Param ("userId") Long userId, @Param ("organizationsId") Long organizationsId);
	
	@Select ("select o_name, part_in_time, introduction, cover from organizations o, user_organization ou where o.o_id=ou.o_id and u_id=#{userId};")
	List<Organization> getJoinedOg (@Param ("userId") Long userId) throws SQLException;
	
}
