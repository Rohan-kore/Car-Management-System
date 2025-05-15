package com.practice.csa.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.practice.csa.Mapper.CarMapper;
import com.practice.csa.Mapper.ServiceMapper;
import com.practice.csa.ResponseDto.CarResponseDto;
import com.practice.csa.ResponseDto.CartResponse;
import com.practice.csa.ResponseDto.ServiceResponseDto;
import com.practice.csa.entity.CarService;
import com.practice.csa.entity.Cart;
import com.practice.csa.exception.UserNotFoundByIdException;
import com.practice.csa.repository.CartRepository;
import com.practice.csa.repository.ServiceRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.service.CartService;
import com.practice.csa.utility.ResponseStructure;

public class CartServiceImpl implements CartService {


	@Autowired
	private UserRepository userRepository; 

	private CartRepository cartRepository;
	@Autowired
	private ServiceMapper serviceMapper;
	@Autowired
	private ServiceRepository serviceRepository;


	@Override
	public ResponseEntity<ResponseStructure<CartResponse>> addServiceToCart(int id) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return   userRepository.findByEmail(email).map(user -> {
			Cart cart =user.getCart();
			if(cart.getCarService() == null) 
				cart.setCarService(new ArrayList());

			return serviceRepository.findById(id).map(service ->{
				cart.getCarService().add(service);
				Cart cart2=cartRepository.save(cart);

				CartResponse cartResponse =new CartResponse();
				cartResponse.setId(cart2.getId());

				List<ServiceResponseDto> services =cart2.getCarService().stream()
						.map(serviceMapper::mapToServiceReponse).toList();
				cartResponse.setService(services);

				return	ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseStructure<CartResponse>()
								.setStatusCode(HttpStatus.CREATED.value())
								.setMessage("Service Added To Cart Succesfully..")
								.setData(cartResponse));
			}).orElseThrow(()-> new UserNotFoundByIdException ("Not Found......"));
		}).orElseThrow(()-> new UserNotFoundByIdException("Not Found"));

	}
	@Override
	public ResponseEntity<ResponseStructure<CartResponse>> removeCarServiceFromCart(int id) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return   userRepository.findByEmail(email).map(user -> {

			Cart cart = user.getCart();

			return	serviceRepository.findById(id).map(excarService ->{
				cart.getCarService().remove(excarService);

				Cart updatedCart=cartRepository.save(cart);

				CartResponse cartResponse = new CartResponse();
				cartResponse.setId(updatedCart.getId());

				List<ServiceResponseDto> service = updatedCart.getCarService().stream()
						.map(serviceMapper::mapToServiceReponse).toList();
				cartResponse.setService(service);
				return	ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseStructure<CartResponse>()
								.setStatusCode(HttpStatus.CREATED.value())
								.setMessage("Service Added To Cart Succesfully..")
								.setData(cartResponse));
			}).orElseThrow(()-> new UserNotFoundByIdException ("Not Found......"));
		}).orElseThrow(()-> new UserNotFoundByIdException("Not Found"));


	}


	@Override
	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> fetchAll() {
	       return	userRepository.findByEmail(SecurityContextHolder.getContext()
	    		   .getAuthentication().getName())
		.map(user -> ResponseEntity.ok(new ResponseStructure<List<ServiceResponseDto>>()
				.setStatusCode(HttpStatus.OK.value())
				.setMessage("Cart Found succesfully")
				.setData(user.getCart().getCarService().stream()
						.map(serviceMapper::mapToServiceReponse).toList())))
		.orElseThrow(()-> new UserNotFoundByIdException("user not foound"));
		            

	}


 
}
