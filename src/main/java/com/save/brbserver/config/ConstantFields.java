package com.save.brbserver.config;

import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author:Zzs
 * @Description: all const string, only getter
 * @DateTime: 2023/4/30 13:45
 **/

@Configuration
public class ConstantFields {
	
	public static String getImagePath () {
		return "/images";
	}
	
	public static String getTimeFullSprite () {
		return "yyyy-MM-dd HH:mm:ss";
	}
	
	public static String HEAD_PATH = "/head";
	public static String IT_HOUSE_IP_PATH = "/ip";
	public static String ACTIVITY_PATH = "/activity";
	
	public static String dateToString (Date date) {
		String strDate = null;
		if (date != null) {
			SimpleDateFormat sdf;
			sdf = new SimpleDateFormat(getTimeFullSprite(), Locale.CHINA);
			strDate = sdf.format(date);
		}
		return strDate;
	}
}
