package com.save.brbserver.customexception;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/11 20:36
 **/
public class FormatException extends Exception {
	public FormatException () {
		super();
	}
	
	public FormatException (String message) {
		super(message);
	}
}
