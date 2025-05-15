package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.ResponseDto.CarResponseDto;
import com.practice.csa.ResponseDto.CartResponse;
import com.practice.csa.ResponseDto.ServiceResponseDto;
import com.practice.csa.repository.CartRepository;
import com.practice.csa.utility.ResponseStructure;

public interface CartService {
	
	ResponseEntity<ResponseStructure<CartResponse>> addServiceToCart(int id);
	ResponseEntity<ResponseStructure<CartResponse>> removeCarServiceFromCart(int id);
	ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> fetchAll();
	

}
