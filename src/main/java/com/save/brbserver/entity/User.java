package com.save.brbserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author:Zzs
 * @Description: bean
 * @DateTime: 2023/4/29 19:20
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@JsonInclude (JsonInclude.Include.NON_NULL)
public class User implements Serializable {
	
	private Long userId;
	
	private String username;
	private String email;
	private String password;
	private String nickname; //new
	private Integer gender;
	private String introduction;
	private String address;
	private String headSculpturePath;
	
	private Timestamp registerTime;
	private Timestamp lastLoginTime;
	private boolean isLoggedIn;
	
	private Long integral;
	private Integer momentsCount;
	private Integer favoriteCount;
	private Integer signInCount;
	private Integer totalSignInCount;
	private Integer signInMaxCount;
	
}
