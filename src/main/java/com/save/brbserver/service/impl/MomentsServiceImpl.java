package com.save.brbserver.service.impl;

import com.save.brbserver.dao.MomentsDao;
import com.save.brbserver.dao.UserDao;
import com.save.brbserver.entity.Moments;
import com.save.brbserver.service.MomentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
	public boolean setCover (Long id, String path) throws SQLException {
		return momentsDao.setCover(id, path);
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public Long postMoments (String username, String text) throws SQLException {
		Long userId = userDao.getUserIdByName(username);
		Moments moments = Moments.builder()
				.userId(userId)
				.content(text)
				.build();
		momentsDao.postMoment(moments); //将圈子存入
		userDao.addIntegral(userId); //为发布者加积分
		return moments.getId(); //返回圈子的主键，为后续传图片指路
	}
	
	@Override
	@Transactional (rollbackFor = Exception.class)
	public List<Map<?, ?>> getAllMoments () {
		//目前的获取方法中，只有一张封面图，另获取发布者名字和发布者头像路径
		
		return null;
	}
	
}
