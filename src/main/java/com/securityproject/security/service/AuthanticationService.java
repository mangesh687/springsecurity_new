package com.securityproject.security.service;

import com.securityproject.security.request.AuthenticationRequest;
import com.securityproject.security.request.RegistorRequest;
import com.securityproject.security.response.AuthanticationResponse;

public interface AuthanticationService {
void register(RegistorRequest input) throws Exception;
AuthanticationResponse login(AuthenticationRequest request);
}
