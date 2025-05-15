package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.ResponseDto.CartResponse;
import com.practice.csa.ResponseDto.ServiceResponseDto;
import com.practice.csa.service.CartService;
import com.practice.csa.utility.ResponseStructure;
@RestController
public class CartController {
	
	
	@Autowired
	private CartService cartService;

	@PreAuthorize("hasAuthority ('CUSTOMER')")
	@PostMapping("/carServices/{carId}/carts")
	ResponseEntity<ResponseStructure<CartResponse>> addServiceToCart( @PathVariable  int id)
	{
		return cartService.addServiceToCart(id);
	}
	@PreAuthorize("hasAuthority ('CUSTOMER')")
	@DeleteMapping("/carServices/{id}/carts")
	ResponseEntity<ResponseStructure<CartResponse>> removeCarServiceFromCart(@PathVariable int id){
		return cartService.removeCarServiceFromCart(id);
	}
	
	@PreAuthorize("hasAuthority ('CUSTOMER')")
	@GetMapping("/carts/services")
	ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> fetchAll(){
		return cartService.fetchAll();

	}




}
