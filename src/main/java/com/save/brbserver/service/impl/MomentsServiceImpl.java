package com.save.brbserver.service.impl;

import com.save.brbserver.customexception.MySecurityException;
import com.save.brbserver.dao.MomentsDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.Comments;
import com.save.brbserver.entity.Moments;
import com.save.brbserver.entity.Picture;
import com.save.brbserver.service.MomentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/16 19:39
 **/

@Service
@Slf4j
public class MomentsServiceImpl implements MomentsService {
	
	@Autowired
	private MomentsDao momentsDao;
	@Autowired
	private UserDao userDao;
	private final int limit = 10;
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public boolean setImages (Long momentId, Integer picId, String path) throws SQLException {
		// 第一张图片应该用作封面
		if (picId == 1)
			return momentsDao.setImages(momentId, picId, path) && setCover(momentId, path);
		else
			return momentsDao.setImages(momentId, picId, path);
	}
	
	@Override
	public boolean setOriginImages (Long momentId, Integer picId, String path) throws SQLException {
		return momentsDao.setOriginImages(momentId, picId, path);
	}
	
	@Override
	public boolean setCover (Long id, String path) throws SQLException {
		return momentsDao.setCover(id, path);
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class) //done
	public Long postMoments (String username, String text) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		Moments moments = Moments.builder()
				.userId(userId)
				.content(text)
				.isLiked(false)
				.build();
		momentsDao.postMoment(moments); //将圈子存入
		userDao.addIntegral(userId); //为发布者加积分
		return moments.getId(); //返回圈子的主键，为后续传图片指路
	}
	
	@Override
	public List<Moments> getAllMoments (String username, int page) throws SQLException {
		//目前的获取方法中，只有一张封面图，另获取发布者名字和发布者头像路径
		Long min = (long) limit * page;
		List<Moments> list = momentsDao.getMomentsList(limit, min);
		if (list.size() != 0) {
			Set<Long> set = momentsDao.findLikesBetweenRange(userDao.getUserIdByName(username),
					list.get(list.size() - 1).getId(), list.get(0).getId());
			log.info(set.toString());
			if (set.size() != 0)
				for (Moments m : list)
					if (set.contains(m.getId()))
						m.setLiked(true);
		}
		
		return list;
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class) //done
	public boolean deleteMoment (String username, Long id) throws SQLException, MySecurityException {
		Long userId = userDao.getUserIdByName(username);
		if (!userId.equals(momentsDao.getMomentUserId(id)))
			throw new MySecurityException();
		return momentsDao.deleteImages(id) || momentsDao.deleteMoments(id);
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public boolean like (String username, Long id) throws SQLException {
		return momentsDao.like(userDao.getUserIdByName(username), id) && momentsDao.likeCount(id);
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public boolean dislike (String username, Long id) throws SQLException {
		return momentsDao.dislike(userDao.getUserIdByName(username), id) && momentsDao.dislikeCount(id);
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public Moments details (Long id, int page) throws Exception {
		Long min = (long) limit * page;
		List<Picture> maps = momentsDao.getImagesDetails(id);
		if (maps == null)
			maps = new ArrayList<>();
		List<Comments> list = momentsDao.getCommentsDetails(id, limit, min);
		if (list == null)
			list = new ArrayList<>();
		Integer count = momentsDao.getCommentCount(id);
		return Moments.builder().id(id).isLiked(false).pictures(maps).commentsCount(count).comments(list).build();
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public void postComment (String username, Long momentId, String comments) throws SQLException {
		Long uId = userDao.getUserIdByName(username);
		momentsDao.postComment(uId, momentId, comments);
	}
	
}
