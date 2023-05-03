package com.save.brbserver.service;

import com.save.brbserver.entity.ItHouseIP;

/**
 * @Author:Zzs
 * @Description: <p>Get all ips, I will tell the clients all the pictures' path, then the clients will ask me for the pictures. So there are two interfaces.</p>
 * @DateTime: 2023/5/2 20:45
 **/

public interface ItHouseIpService {
	
	ItHouseIP getIP ();
	
	
}
