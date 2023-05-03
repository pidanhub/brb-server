package com.save.brbserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author:Zzs
 * @Description: 文创，分类处理各个专区的请求
 * @DateTime: 2023/5/3 23:07
 **/

@Mapper
public interface IpDao {
	
	@Select ("select * from ip_type where type = #{type}")
	int selectIpPrefecture (@Param ("type") String type);
	
}
