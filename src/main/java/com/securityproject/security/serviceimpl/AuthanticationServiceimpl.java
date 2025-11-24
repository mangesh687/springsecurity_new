package com.securityproject.security.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securityproject.security.Entity.Authority;
import com.securityproject.security.Entity.User;
import com.securityproject.security.repository.UserRepository;
import com.securityproject.security.request.AuthenticationRequest;
import com.securityproject.security.request.RegistorRequest;
import com.securityproject.security.response.AuthanticationResponse;
import com.securityproject.security.service.AuthanticationService;
import com.securityproject.security.service.Jwtservice;



@Service
public class AuthanticationServiceimpl implements AuthanticationService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private Jwtservice jwtservice;

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
	@Override
	@Transactional(readOnly = true)
	public AuthanticationResponse login(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
				);
		User user=userRepository.findByEmail(request.getEmail())
				.orElseThrow(()->new IllegalArgumentException("invalid email or passwords"));
		String jwtToken=jwtservice.generatedToken(new HashMap<>(), user);
		return new AuthanticationResponse(jwtToken);
	}
	

}
