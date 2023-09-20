package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.models.UserResgistraion;
import com.crud.sevice.UserResgistrationService;

@RestController
@RequestMapping("/users")
public class UserRegistrationcontroller {
	
	@Autowired
	UserResgistrationService userResgistrationService;
	@PostMapping("/register")
	public ResponseEntity<?> registerUser (@RequestBody   UserResgistraion userResgistraion)
	{
		return userResgistrationService.registerUser(userResgistraion);
	}

}
