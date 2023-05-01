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
	
	public Long getUserId () {
		return userId;
	}
	
	public void setUserId (Long userId) {
		this.userId = userId;
	}
	
	public String getUsername () {
		return username;
	}
	
	public void setUsername (String username) {
		this.username = username;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public String getGender () {
		return gender;
	}
	
	public void setGender (String gender) {
		this.gender = gender;
	}
	
	public String getIntroduction () {
		return introduction;
	}
	
	public void setIntroduction (String introduction) {
		this.introduction = introduction;
	}
	
	public String getAddress () {
		return address;
	}
	
	public void setAddress (String address) {
		this.address = address;
	}
	
	public String getHeadSculpturePath () {
		return headSculpturePath;
	}
	
	public void setHeadSculpturePath (String headSculpturePath) {
		this.headSculpturePath = headSculpturePath;
	}
	
	public Timestamp getRegisterTime () {
		return registerTime;
	}
	
	public void setRegisterTime (Timestamp registerTime) {
		this.registerTime = registerTime;
	}
	
	public Timestamp getLastLoginTime () {
		return lastLoginTime;
	}
	
	public void setLastLoginTime (Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
