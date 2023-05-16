package com.save.brbserver.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

/**
 * @Author:Zzs
 * @Description: <p>moments，moments_images</p>
 * @DateTime: 2023/5/15 11:03
 **/

@Mapper
public interface MomentsDao {
	
	@Insert ("insert into moments_images(#{momentId}, #{picId}, #{path});")
	boolean setImages (@Param ("momentId") Long id1, @Param ("picId") Integer id2, @Param ("path") String path) throws SQLException;
	
}
