package com.securityproject.security.service;

import com.securityproject.security.request.RegistorRequest;

public interface AuthanticationService {
void register(RegistorRequest input) throws Exception;
}
