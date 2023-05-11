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
	@JsonInclude (JsonInclude.Include.NON_EMPTY)
	private T data;
	@JsonInclude (JsonInclude.Include.NON_EMPTY)
	private String message;
	
	public static final int SUCCESS = 200;
	public static final int TOKEN_OUT_OF_TIME = 401;
	public static final int NO_TOKEN = 403;
	public static final int METHOD_NOT_ALLOW = 405;
	public static final int FAILED = 500;
	public static final int DANGEROUS = 100;
	
}
