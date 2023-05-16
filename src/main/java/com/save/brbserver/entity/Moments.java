package com.save.brbserver.entity;

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
class Moments {
	
	private Long id;
	private Long userId;
	private String username;
	private String content;
	private Timestamp postTime;
	private Set<String> pictures;
	private String coverPath;
}
