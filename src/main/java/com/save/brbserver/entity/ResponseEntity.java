package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:Zzs
 * @Description: 用于返回给客户端，一共两部分，返回码以及数据体data，比如二进制图片文件和活动的list
 * @DateTime: 2023/5/1 23:52
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
	
	private int code;
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private T data;
	@JsonInclude (JsonInclude.Include.NON_EMPTY)
	private String message;
	
	public static final int SUCCESS = 200;
	public static final int TOKEN_OUT_OF_TIME = 401;
	public static final int NO_TOKEN = 403;
	public static final int METHOD_NOT_ALLOW = 405;
	public static final int FAILED = 500;
	public static final int DANGEROUS = 100;
	public static final int FORMAT_NOT_RIGHT = 101;
	public static final int UNIQUE_FIELD_ALREADY_EXIST = 102;
	public static final int WRONG_NUMBERINGS = 103;
	public static final int SIGN_IN_LESS_THAN_ONE_DAY = 104;
	public static final int VERIFY_CODE_WRONG = 105;
	public static final int WRONG_PASSWORD = 106;
	
	public static final int NETWORK_UNREACHABLE = 1000;
	public static final int EMAIL_SEND_FAILED = 1001;
	public static final int VERIFY_CODE_EXIST = 999;
	public static final int ACTIVITY_ALREADY_END = 1002;
	
}
