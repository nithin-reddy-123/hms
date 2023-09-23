package com.hms.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hms.entities.User;
import com.hms.web.UserRegistration;

public interface UserService extends UserDetailsService{
	
	User save(UserRegistration userRegistration);
}
