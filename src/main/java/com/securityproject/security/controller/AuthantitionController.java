package com.securityproject.security.controller;

import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.securityproject.security.request.RegistorRequest;
import com.securityproject.security.serviceimpl.AuthanticationServiceimpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name="Authentication Rest API Endpoint" , description = "operarion related to register and login")
public class AuthantitionController {

	@Autowired
	private AuthanticationServiceimpl authanticationServiceimpl;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Register a user", description = "created a user")
	public void register(@Valid @RequestBody RegistorRequest registorRequest) throws Exception {
		authanticationServiceimpl.register(registorRequest);
	}
	@GetMapping("/home")
	public String home() {
		return "this is new home page";
	}
	
}
