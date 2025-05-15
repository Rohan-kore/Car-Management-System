package com.practice.csa.Mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.RequestDto.ServiceRequestDto;
import com.practice.csa.ResponseDto.ServiceResponseDto;
import com.practice.csa.entity.CarService;

@Component
public class ServiceMapper {
	
	public CarService mapToService(ServiceRequestDto ser) {
		
		CarService sc = new CarService();
		sc.setType(ser.getType());
		sc.setCost(ser.getCost());
		sc.setDescription(ser.getDescription());
		
		return sc;
	}
	public ServiceResponseDto mapToServiceReponse(CarService carService) {
		
		ServiceResponseDto dto= new ServiceResponseDto();
		dto.setId(carService.getId());
		dto.setCost(carService.getCost());
		dto.setType(carService.getType());
		dto.setDescription(carService.getDescription());
		
		
		return dto;
		
	}

}
