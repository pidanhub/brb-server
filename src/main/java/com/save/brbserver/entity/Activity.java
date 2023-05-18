package com.save.brbserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/4/30 15:12
 **/

@SuppressWarnings ("SpellCheckingInspection")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity {
	
	private Long activId;
	private String name;
	private Long belongsTo;
	private String activInfo;
	private Timestamp startTime;
	private String activLocation;
	private Boolean isEnd;
	
}
