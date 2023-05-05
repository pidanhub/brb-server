package com.save.brbserver.dao;

import com.save.brbserver.entity.ItHouseIP;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author:Zzs
 * @Description: 文创，分类处理各个专区的请求
 * @DateTime: 2023/5/3 23:07
 **/

@Mapper
public interface IpDao {
	
	@Select ("select id from ip_type where type = #{type}")
	int selectIpPrefecture (@Param ("type") String type) throws SQLException;
	
	@Select ("select * from ips where ip_type=#{ipType}")
	List<ItHouseIP> selectAllIPsOfCurrentPrefecture (@Param ("ipType") int type) throws SQLException;
	
	@Select ("select * from ips where name=#{name}")
	List<ItHouseIP> selectOneIPByName (@Param ("name") String name) throws SQLException;
	
}
