package com.save.brbserver.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author:Zzs
 * @Description: all const string, only getter
 * @DateTime: 2023/4/30 13:45
 **/

public class ConstantFields {
	
	public static String getImagePath () {
		return "/images";
	}
	
	public static String getTimeFullSprite () {
		return "yyyy-MM-dd HH:mm:ss";
	}
	
	public static final String HEAD_PATH = "/head";
	public static final String IT_HOUSE_IP_PATH = "/shop";
	public static final String ACTIVITY_PATH = "/activity";
	public static final String MOMENTS_PATH = "/moments";
	
	public static final int FAVORITE_TYPE_ADD = 1;
	public static final int FAVORITE_TYPE_DELETE = 3;
	
	public static String dateToString (Date date) {
		String strDate = null;
		if (date != null) {
			SimpleDateFormat sdf;
			sdf = new SimpleDateFormat(getTimeFullSprite(), Locale.CHINA);
			strDate = sdf.format(date);
		}
		return strDate;
	}
	
	public enum FILE_OPTION {
		HEAD,
		ACTIVITY
	}
	
}
