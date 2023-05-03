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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activities {
	
	private Integer activId;
	private Integer belongsTo;
	private String activInfo;
	private Timestamp startTime;
	private String activLocation;
	private boolean isEnd;
	
}
