package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @Author:Zzs
 * @Description:
 * @DateTime: 2023/5/18 17:22
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class SaveStationEntity {
	private Integer id;
}
