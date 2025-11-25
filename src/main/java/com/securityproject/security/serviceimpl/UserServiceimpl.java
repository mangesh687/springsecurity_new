package com.securityproject.security.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securityproject.security.Entity.User;
import com.securityproject.security.repository.UserRepository;
import com.securityproject.security.response.UserResponse;
import com.securityproject.security.service.UserService;
@Service
public class UserServiceimpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	@Transactional(readOnly = true)
	public UserResponse getUserInfo() {
     Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
     if(authentication==null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
    	 throw new AccessDeniedException("Authentication required");
     }
		User user= (User)authentication.getPrincipal();
		 return new UserResponse(
	                user.getId(),
	                user.getFirstname() + " " + user.getLastname(),
	                user.getEmail(),
	                user.getAuthorities()
	        );
	    }

}
