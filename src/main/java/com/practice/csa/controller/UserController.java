package com.practice.csa.controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.RequestDto.UserRequest;
import com.practice.csa.ResponseDto.UserResponse;
import com.practice.csa.security.JwtService;
import com.practice.csa.serviceImpl.UserServiceImpl;
import com.practice.csa.utility.ResponseStructure;


@RestController
public class UserController {
	@Autowired
	private  UserServiceImpl userServiceImpl;

	
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/user-login")
    public  String login(){
		return jwtService.createJwt("admin@gmail.com", "ADMIN", Duration.ofDays(1));
	}
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest){
		return userServiceImpl.registerUser(userRequest);
	}
	
	@PutMapping("/register/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest){
		return userServiceImpl.updateUser(userRequest);
	}
	
	@DeleteMapping("/register/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(@PathVariable int id){
		return userServiceImpl.deleteUserById(id);
	}
	
	
	@GetMapping("/register/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int id){
		return userServiceImpl.findUserById(id);
	}
	@GetMapping("/register")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll(){
		return userServiceImpl.findAll();
	}
	

}
