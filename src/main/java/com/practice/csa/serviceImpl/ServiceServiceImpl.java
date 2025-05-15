package com.practice.csa.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.apache.coyote.http11.Http11InputBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.Mapper.ServiceMapper;
import com.practice.csa.RequestDto.ServiceRequestDto;
import com.practice.csa.ResponseDto.CarResponseDto;
import com.practice.csa.ResponseDto.ServiceResponseDto;
import com.practice.csa.entity.Car;
import com.practice.csa.entity.CarService;
import com.practice.csa.exception.ServiceNotFoundException;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.repository.ServiceRepository;
import com.practice.csa.service.ServiceService;
import com.practice.csa.utility.ResponseStructure;

import ch.qos.logback.core.status.Status;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private ServiceMapper serviceMapper;



	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(ServiceRequestDto service) {
    CarService cs = serviceRepository.save(serviceMapper.mapToService(service));
	ServiceResponseDto serviceResponseDto = serviceMapper.mapToServiceReponse(cs);
	
	return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new ResponseStructure<ServiceResponseDto>()
					.setStatusCode(HttpStatus.CREATED.value())
					.setMessage("Car Service Added Succesfully..")
					.setData(serviceResponseDto));
	}
	



	
	
	
	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>>findServiceById(int id ) {
		return serviceRepository.findById(id).map(service -> ResponseEntity 
				.status(HttpStatus.FOUND)
				.body(new ResponseStructure<ServiceResponseDto>()
						.setStatusCode(HttpStatus.FOUND.value()) 
						.setMessage("jhbjh")
						.setData( serviceMapper.mapToServiceReponse(service))))
				.orElseThrow(()-> new ServiceNotFoundException("Service Nit Found"));
	}



	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> updateServiceById(int id, ServiceRequestDto  updatedService) {

	return	serviceRepository.findById(id).map(exService -> {
			CarService cs = serviceMapper.mapToService(updatedService);
			cs.setId(exService.getId());
		return	ResponseEntity.status( HttpStatus.OK).body(new ResponseStructure<ServiceResponseDto>()
				    .setStatusCode(HttpStatus.OK.value())
					.setMessage("Car Service Updated Succesfully")
					.setData(serviceMapper.mapToServiceReponse(serviceRepository.save(cs))));
			
			
		}).orElseThrow(()-> new ServiceNotFoundException("Get Lost..."));
		
	
	}


	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> deleteServiceById(int id ){
		return	serviceRepository.findById(id).map(service -> {
			serviceRepository.delete(service);
		return	ResponseEntity.status( HttpStatus.OK).body(new ResponseStructure<ServiceResponseDto>()
				    .setStatusCode(HttpStatus.OK.value())
					.setMessage("Car Service Updated Succesfully")
					.setData(serviceMapper.mapToServiceReponse(service)));
			
			
		}).orElseThrow(()-> new ServiceNotFoundException("Get Lost..."));
		

	}

	@Override
	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAllService() {
		List<ServiceResponseDto>list =serviceRepository.findAll().stream().map(num -> serviceMapper.mapToServiceReponse(num)).toList();
		ResponseStructure <List<ServiceResponseDto>> rs = new ResponseStructure<List<ServiceResponseDto>>();
		rs.setStatusCode(HttpStatus.FOUND.value());
		rs.setMessage("Service Object  Found Succesfully ....");
		rs.setData(list);

		return new ResponseEntity<ResponseStructure<List<ServiceResponseDto>>>(rs,HttpStatus.FOUND);
	}



	

}
