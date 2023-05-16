package com.save.brbserver.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @Author:Zzs
 * @Description: 控制两个表，moments，moments_images
 * @DateTime: 2023/5/15 11:02
 **/

@Service
public interface MomentsService {
	
	boolean setImages (Long momentId, Integer picId, String path) throws SQLException;
	
}
