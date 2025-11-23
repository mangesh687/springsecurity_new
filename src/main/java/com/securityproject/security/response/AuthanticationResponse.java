package com.securityproject.security.response;

public class AuthanticationResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthanticationResponse(String token) {
		super();
		this.token = token;
	}
	

}
