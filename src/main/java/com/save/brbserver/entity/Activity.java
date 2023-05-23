package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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

@SuppressWarnings ("all")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Setter
//@Getter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class Activity {
	
	private Long activId;
	private String name;
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private Long belongsTo;
	private String activInfo;
	private Timestamp startTime;
	private String activLocation;
	private Boolean isEnd;
	
	private String organizationName;
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private Boolean isSignedUp;
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private Boolean isSignedIn;
	
}
