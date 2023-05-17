package com.save.brbserver.dao;

import com.save.brbserver.entity.Moments;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;

/**
 * @Author:Zzs
 * @Description: <p>moments，moments_images</p>
 * <p>可以发布一条朋友圈，也可以显示一些简单的朋友圈并附上一张缩略图，也可以查看详情{全部内容，发布者，发布者头像，用户名，}，删除朋友圈，</p>
 * <a href="C:\Users\HP\Pictures\Web\BG1.png">l</a>
 * @DateTime: 2023/5/15 11:03
 **/

@Mapper
public interface MomentsDao {
	
	@Insert ("insert into moments_images values (#{momentId}, #{picId}, #{path});")
	boolean setImages (@Param ("momentId") Long id1, @Param ("picId") Integer id2, @Param ("path") String path) throws SQLException;
	
	@Insert ("insert into moments(user_id, content, post_time) values(#{userId}, #{content}, now());")
	@Options (useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void postMoment (Moments moments) throws SQLException;
	
	@Update ("update moments set cover_path=#{path} where id=#{id};")
	boolean setCover (@Param ("id") Long id, @Param ("path") String path) throws SQLException;
	
	//
	@Select ("")
	Moments selectMoments () throws SQLException;
	
}
