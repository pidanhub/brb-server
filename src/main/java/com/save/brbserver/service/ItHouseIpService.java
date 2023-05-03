package com.save.brbserver.service;

import com.save.brbserver.entity.ItHouseIP;

import java.util.List;

/**
 * @Author:Zzs
 * @Description: <p>Get all ips, I will tell the clients all the pictures' path, then the clients will ask me for the pictures. So there are two interfaces.</p>
 * @DateTime: 2023/5/2 20:45
 **/

public interface ItHouseIpService {
	
	List<ItHouseIP> getIP ();
	
	int getTypeId (String type);
	
}
