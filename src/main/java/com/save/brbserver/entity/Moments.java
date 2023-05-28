package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

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
public class Moments {
	
	private Long id; //1
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private Long userId;
	private String content; //1
	private Timestamp postTime; //1
	private String coverPath; //1
	private Long like; //1
	private boolean isLiked; //1
	
	private String nickname; //1
	private String userHeadPath; //1
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private List<Picture> pictures;
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private Integer commentsCount;
	@JsonInclude (JsonInclude.Include.NON_NULL)
	private List<Comments> comments;
	
}
