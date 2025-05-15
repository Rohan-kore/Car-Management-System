package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.RequestDto.CarRequestDto;
import com.practice.csa.ResponseDto.CarResponseDto;
import com.practice.csa.entity.Car;
import com.practice.csa.utility.ResponseStructure;

public interface CarServiceIn {
	
	public ResponseEntity<ResponseStructure<CarResponseDto>>addcar(CarRequestDto car);
	
	public ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(int carId);
	
	public ResponseEntity<ResponseStructure<CarResponseDto>> updateCarById(int carId ,CarRequestDto updatedCar);
	
	public ResponseEntity<ResponseStructure<CarResponseDto>> deleteCarById(int carId);
	
	public ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCar();


	

}
