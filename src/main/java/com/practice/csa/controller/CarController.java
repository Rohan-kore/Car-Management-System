package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.RequestDto.CarRequestDto;
import com.practice.csa.ResponseDto.CarResponseDto;
import com.practice.csa.entity.Car;
import com.practice.csa.service.CarServiceIn;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class CarController {
	
	@Autowired
	private CarServiceIn carServiceIn;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/cars")
	public ResponseEntity<ResponseStructure<CarResponseDto>> addcar(@RequestBody CarRequestDto car){
		return carServiceIn.addcar(car);
	}


	@GetMapping("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(@PathVariable int carId){
		return carServiceIn.findCarById(carId);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponseDto>> updateCarById(@PathVariable  int carId ,@RequestBody CarRequestDto car){
		return carServiceIn.updateCarById(carId, car);
		
		
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponseDto>> deleteCarById(@PathVariable   int carId){
		return carServiceIn.deleteCarById(carId);
		
				
	}
	
	@GetMapping("/cars")
	public ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCar(){
		return carServiceIn.findAllCar();
	}
}
