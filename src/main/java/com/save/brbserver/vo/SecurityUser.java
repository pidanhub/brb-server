package com.save.brbserver.vo;

import com.save.brbserver.entity.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author:Zzs
 * @Description: The entity class only comunicates with the databases, where the design business logic should be placed right here.
 * @DateTime: 2023/4/29 21:02
 **/
@Getter
@ToString
public class SecurityUser implements UserDetails {
	
	@Autowired
	private final User user;
	public SecurityUser(User user) { this.user=user; }
	
	/**
	 * TODO 权限
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities () {
		return null;
	}
	
	@Override
	public String getPassword () {
		return this.user.getPassword();
	}
	
	@Override
	public String getUsername () {
		return this.user.getUsername();
	}
	
	/**
	 * TODO need to implement token then write this judge function
	 * @Description: Whether the account is overdue.
	 * @return boolean
	 */
	@Override
	public boolean isAccountNonExpired () {
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked () {
		return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired () {
		return false;
	}
	
	@Override
	public boolean isEnabled () {
		return false;
	}
	
	
}
