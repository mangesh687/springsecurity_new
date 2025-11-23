package com.securityproject.security.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securityproject.security.Entity.Authority;
import com.securityproject.security.Entity.User;
import com.securityproject.security.repository.UserRepository;
import com.securityproject.security.request.RegistorRequest;
import com.securityproject.security.service.AuthanticationService;

import jakarta.transaction.Transactional;

@Service
public class AuthanticationServiceimpl implements AuthanticationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void register(RegistorRequest input) throws Exception {
		if(isEmailTaken(input.getEmail())) {
			throw new Exception("Email alreday taken");
		}
       User user=buildNewUser(input);
       userRepository.save(user);
	}
	private boolean isEmailTaken(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
	private User buildNewUser(RegistorRequest input) {
		User user=new User();
		user.setId(0);
		user.setFirstname(input.getFirstname());
		user.setLastname(input.getLastname());
		user.setEmail(input.getEmail());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setAuthorities(initialAuthority());
		
		return user;
	}
	private List<Authority> initialAuthority(){
		boolean isFirstUser=userRepository.count()==0;
		List<Authority> auth=new ArrayList<>();
		auth.add(new Authority("Role_EMPLOYEE"));
		if(isFirstUser) {
			auth.add(new Authority("ROLE_ADMIN"));
		}
		return auth;
		
	}
	

}
