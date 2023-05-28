package com.save.brbserver.dao;

import com.save.brbserver.entity.Comments;
import com.save.brbserver.entity.Moments;
import com.save.brbserver.entity.Picture;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

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
	
	//按时间先后排序，返回后发布的10个
	@Select ("select id, content, post_time, cover_path, `like`, nickname, head_sculpture_path as user_head_path " +
			"from moments, users " +
			"where moments.user_id = users.user_id " +
			"order by post_time desc " +
			"limit #{limit} offset #{offset};")
	List<Moments> getMomentsList (@Param ("limit") int limit, @Param ("offset") Long offset) throws SQLException;
	
	@Select ("select photo_num, storage_path from moments_images where moment_id = #{id};")
	List<Picture> getImagesDetails (@Param ("id") Long id) throws SQLException;
	
	@Select ("select count(*) from moments_comments where moment_id=#{id};")
	Integer getCommentCount (@Param ("id") Long id) throws SQLException;
	
	@Select ("select comment_id, moment_id, content, post_time, nickname, head_sculpture_path as user_head_path " +
			"from moments_comments, users " +
			"where moments_comments.user_id = users.user_id and moment_id = #{id} " +
			"order by post_time desc " +
			"limit #{limit} offset #{offset};")
	List<Comments> getCommentsDetails (@Param ("id") Long momentId, @Param ("limit") int limit, @Param ("offset") Long offset) throws SQLException;
	
	@Select ("select user_id from moments where id=#{id};")
	Long getMomentUserId (@Param ("id") Long id) throws SQLException;
	
	@Delete ("delete from moments where id=#{id};")
	boolean deleteMoments (@Param ("id") Long id) throws SQLException;
	
	@Delete ("delete from moments_images where moment_id=#{id};")
	boolean deleteImages (@Param ("id") Long id) throws SQLException;
	
	@Insert ("insert into user_like_moment values(#{u}, #{m});")
	boolean like (@Param ("u") Long userId, @Param ("m") Long momentId) throws SQLException;
	
	@Update ("update moments set `like` = `like` + 1 where id = #{m};")
	boolean likeCount (@Param ("m") Long id) throws SQLException;
	
	@Select ("select moment_id from user_like_moment where user_id=#{userId} and moment_id>=#{min} and moment_id<=#{max};")
	Set<Long> findLikesBetweenRange (@Param ("userId") Long userId, @Param ("min") Long min, @Param ("max") Long max) throws SQLException;
	
	@Delete ("delete from user_like_moment where user_id=#{u} and moment_id=#{m};")
	boolean dislike (@Param ("u") Long userId, @Param ("m") Long id) throws SQLException;
	
	@Update ("update moments set `like` = `like` - 1 where id = #{m};")
	boolean dislikeCount (@Param ("m") Long id) throws SQLException;
	
	@Insert ("insert into moments_comments(user_id, moment_id, content) values(#{u}, #{m}, #{content});")
	@Options (useGeneratedKeys = true, keyProperty = "comment_id", keyColumn = "comment_id")
	void postComment (@Param ("u") Long userId, @Param ("m") Long id, @Param ("content") String content) throws SQLException;
	
	
}
