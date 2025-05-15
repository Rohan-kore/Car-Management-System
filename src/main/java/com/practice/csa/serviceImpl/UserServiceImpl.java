package com.practice.csa.serviceImpl;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.csa.Mapper.UserMapper;
import com.practice.csa.RequestDto.UserRequest;
import com.practice.csa.ResponseDto.UserResponse;
import com.practice.csa.entity.Cart;
import com.practice.csa.entity.User;
import com.practice.csa.enums.UserRole;
import com.practice.csa.exception.UserNotFoundByIdException;

import com.practice.csa.repository.CartRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{


	private  UserRepository userRepository;
	private  UserMapper userMapper;
	private  CartRepository cartRepository;




	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, CartRepository cartRepository) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.cartRepository = cartRepository;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest){
		User user = userMapper.mapToUser(userRequest);
		user =userRepository.save(user);

		if(user.getUserRole().equals(UserRole.CUSTOMER)) {

			Cart cart = new Cart();
			cart.setUser(user);
			cartRepository.save(cart);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<UserResponse>()
				.setStatusCode(HttpStatus.CREATED.value())
				.setMessage("User Object Created Succesfully..")
				.setData(userMapper.mapToUserResponse(user)));


	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(UserRequest userRequest) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(name).map(existingUser -> {
			User user= userMapper.mapToUser(userRequest);
			user.setId(existingUser.getId());
			user=userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<UserResponse>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("User Object Updated")
							.setData(userMapper.mapToUserResponse(user)));
		}).orElseThrow(() ->new UserNotFoundByIdException("User Id Not Found.."));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int id) {
		return userRepository.findById(id).map(user -> {
			userRepository.deleteById(user.getId());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<UserResponse>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("User Object Deleted Succesfully")
							.setData(userMapper.mapToUserResponse(user)));

		}).orElseThrow(()-> new UserNotFoundByIdException("User Id Deleterd"));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int id) {
		return userRepository.findById(id).map(user-> ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<UserResponse>().setStatusCode(HttpStatus.FOUND.value()).setMessage("User Object Found..").setData(userMapper.mapToUserResponse(user)))
				).orElseThrow(()-> new UserNotFoundByIdException("User Object Found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll() {
		List<UserResponse> user = userRepository.findAll().stream().map(userMapper::mapToUserResponse).toList();
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<UserResponse>>()
						.setStatusCode(HttpStatus.FOUND.value())
						.setMessage("User Object Deleted Succesfully")
						.setData(user));

	}
}

