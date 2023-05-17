package com.save.brbserver.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author:Zzs
 * @Description: 控制两个表，moments，moments_images
 * @DateTime: 2023/5/15 11:02
 **/

@Service
public interface MomentsService {
	
	boolean setImages (Long momentId, Integer picId, String path) throws SQLException;
	
	boolean setCover (Long id, String path) throws SQLException;
	
	Long postMoments (String username, String text) throws SQLException;
	
	List<Map<?, ?>> getAllMoments ();
}
