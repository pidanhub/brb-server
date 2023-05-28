package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/27 23:34
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Comments {
	
	private Long commentId;
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private Long momentId;
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private Long userId;
	private String content;
	private Timestamp postTime;
	
	private String nickname;
	private String userHeadPath;
	
}
