package com.practice.csa.service;

import java.util.List;


import org.springframework.http.ResponseEntity;

import com.practice.csa.RequestDto.ServiceRequestDto;
import com.practice.csa.ResponseDto.ServiceResponseDto;
import com.practice.csa.entity.CarService;
import com.practice.csa.utility.ResponseStructure;

public interface ServiceService {

	public ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(ServiceRequestDto service);

	public ResponseEntity<ResponseStructure<ServiceResponseDto>> findServiceById(int id );
	
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> updateServiceById(int id , ServiceRequestDto updatedService);
	
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> deleteServiceById(int id );

	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAllService();

}
