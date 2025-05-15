package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.RequestDto.UserRequest;
import com.practice.csa.ResponseDto.UserResponse;
import com.practice.csa.utility.ResponseStructure;

public interface UserService {
	
	ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest);
	
	ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest);
	
	ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int id);
	
	ResponseEntity<ResponseStructure<UserResponse>> findUserById( int id);
	
	ResponseEntity<ResponseStructure<List<UserResponse>>>findAll();

}
