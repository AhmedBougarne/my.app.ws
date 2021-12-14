package com.learnspring.app.ws.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnspring.app.ws.requests.UserRequest;
import com.learnspring.app.ws.responses.UserResponse;
import com.learnspring.app.ws.services.UserService;
import com.learnspring.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/users") // localhost:8080/users
public class UserController {
	
	 @Autowired
	UserService userService;
	
	@GetMapping(path="/{id}",produces= {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userDto=userService.getUserByUserId(id);
		
		UserResponse userResponse=new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	@PostMapping(consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			    )
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		
		
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto createUser=userService.createUser(userDto);
		
		UserResponse userResponse=new UserResponse();
		
		BeanUtils.copyProperties(createUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.CREATED);
	}
	@PutMapping(path="/{id}",
			         consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			         produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
	           )
	public  ResponseEntity<UserResponse> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		
		UserDto updateUser=userService.updateUser(id,userDto);
		
		UserResponse userResponse=new UserResponse();
		
		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.ACCEPTED);
	}
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
