package com.securityproject.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {
	@NotEmpty(message = "email is mandatory")
	@Email(message = "Invalid email formate")
	private String email;
	@NotEmpty(message = "password is mandatory")
	@Size(min = 3,max=20,message = "password must at lest 3 charaters")
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthenticationRequest(
			@NotEmpty(message = "email is mandatory") @Email(message = "Invalid email formate") String email,
			@NotEmpty(message = "password is mandatory") @Size(min = 3, max = 20, message = "password must at lest 3 charaters") String password) {
		super();
		this.email = email;
		this.password = password;
	}
	

}
