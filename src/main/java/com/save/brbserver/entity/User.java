package com.save.brbserver.entity;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

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
public class User implements Serializable {
	
	private Long userId;
	
	private String username;
	private String email;
	private String password;
	private String gender;
	private String introduction;
	private String address;
	private String headSculpturePath;
	
	private Timestamp registerTime;
	private Timestamp lastLoginTime;
	private boolean isLoggedIn;
	
}
