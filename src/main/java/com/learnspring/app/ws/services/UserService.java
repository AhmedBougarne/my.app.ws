package com.learnspring.app.ws.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.learnspring.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String id,UserDto userDto);
	
	void deleteUser(String userId);
		
 
}
