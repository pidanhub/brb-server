package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/2 17:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class ItHouseIP {
	private Integer ipId;
	private Integer ipType;
	private String name;
	private Double price;
	private Long views;
	private Double star;
	private Long owner;
	private String coverPath;
	private String info;
	
	private Boolean isFavorite;
}
