package com.securityproject.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securityproject.security.Entity.User;
import com.securityproject.security.serviceimpl.UserServiceimpl;

import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/user")
@Tag(name="user Rest controller", description = "Operation related  User Rest controller")
public class UserController {
	
	@Autowired
	private UserServiceimpl serviceimpl;
	@GetMapping("/info")
	public User getUserInfo() {
		return serviceimpl.getUserInfo();
	}

}
