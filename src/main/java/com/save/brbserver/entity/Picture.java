package com.save.brbserver.entity;

import lombok.*;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/28 11:34
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Picture {
	private Integer photoNum;
	private String storagePath;
}
