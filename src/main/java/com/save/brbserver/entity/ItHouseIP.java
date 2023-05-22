package com.save.brbserver.entity;

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
	
	private boolean isFavorite;
}
