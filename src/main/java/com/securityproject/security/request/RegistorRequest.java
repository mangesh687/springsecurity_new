package com.securityproject.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegistorRequest {
	@NotEmpty(message = "first name is mandatory")
	@Size(min=3,max=30,message="first name must be at least 3 charaters")
	private String firstname;
	@NotEmpty(message = "last name is mandatory")
	@Size(min=3,max=30,message="last name must be at least 3 charaters")
	private String lastname;
	@NotEmpty(message = "Email is mandatory")
	@Email(message = "invalid email formate")
	private String email;
	@NotEmpty(message = "password is mandatory")
	@Size(min=3,max=30,message="password must be at least 5 charaters")
	private String password;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
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
	public RegistorRequest(
			@NotEmpty(message = "first name is mandatory") @Size(min = 3, max = 30, message = "first name must be at least 3 charaters") String firstname,
			@NotEmpty(message = "last name is mandatory") @Size(min = 3, max = 30, message = "last name must be at least 3 charaters") String lastname,
			@NotEmpty(message = "Email is mandatory") @Email(message = "invalid email formate") String email,
			@NotEmpty(message = "password is mandatory") @Size(min = 3, max = 30, message = "password must be at least 5 charaters") String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

}
