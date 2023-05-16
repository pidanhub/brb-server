package com.save.brbserver.service.impl;

import com.save.brbserver.dao.MomentsDao;
import com.save.brbserver.service.MomentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

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
	
	@Override
	public boolean setImages (Long momentId, Integer picId, String path) throws SQLException {
		return momentsDao.setImages(momentId, picId, path);
	}
	
	
}
