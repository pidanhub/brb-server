package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/15 10:52
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class Moments {
	
	private Long id; //1
	private Long userId;
	private String content; //1
	private Timestamp postTime; //1
	private String coverPath; //1
	private Long like; //1
	private boolean isLiked; //1
	
	private String username; //1
	private String userHeadPath; //1
	private Set<String> pictures;
	
}
