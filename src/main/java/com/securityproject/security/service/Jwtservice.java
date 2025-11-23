package com.securityproject.security.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface Jwtservice {
	String extractUsername(String token);
	boolean isTokenValid(String token,UserDetails userDetails);
	String generatedToken(Map<String, Object> claims,UserDetails userDetails);

}
 