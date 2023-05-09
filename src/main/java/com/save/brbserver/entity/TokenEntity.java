package com.save.brbserver.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/7 23:42
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {
	
	private String toAuthentication;
	private String toRefresh;
//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
//	private T payload;
	
}
