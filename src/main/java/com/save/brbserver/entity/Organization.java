package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 15:53
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@JsonInclude (JsonInclude.Include.NON_NULL)
public class Organization {
	
	private Long oId;
	private String oName;
	private Long whoCreate;
	private String introduction;
	private Timestamp createTime;
	private String cover;
	
	private Timestamp partInTime;
	
}
