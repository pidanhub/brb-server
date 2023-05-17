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
@JsonInclude (JsonInclude.Include.NON_EMPTY)
public class Moments {
	
	private Long id;
	private Long userId;
	private String content;
	private Timestamp postTime;
	private String coverPath;
	private Long like;
	
	private String username;
	private String userHeadPath;
	private Set<String> pictures;
	
}
