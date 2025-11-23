package com.securityproject.security.Entity;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Embeddable;

@Embeddable
public class Authority implements GrantedAuthority {
	
	private String authority;

	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authority(String authority) {
		super();
		this.authority = authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public @Nullable String getAuthority() {
		// TODO Auto-generated method stub
		return authority;
	}
	

}
