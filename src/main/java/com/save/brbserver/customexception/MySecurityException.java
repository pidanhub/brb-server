package com.save.brbserver.customexception;

/**
 * @Author:Zzs
 * @Description: <p>自定义异常类,告知攻击者不允许请求</p>
 * @DateTime: 2023/5/11 15:02
 **/

public class MySecurityException extends Exception {
	public MySecurityException (String message) {
		super(message);
	}
	
	public MySecurityException () {
		super();
	}
}
