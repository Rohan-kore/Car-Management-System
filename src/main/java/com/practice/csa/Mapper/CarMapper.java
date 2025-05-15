package com.practice.csa.Mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.RequestDto.CarRequestDto;
import com.practice.csa.ResponseDto.CarResponseDto;
import com.practice.csa.entity.Car;

@Component
public class CarMapper {
	
	public Car mapToCar(CarRequestDto cr) {
	Car c = new Car();
	c.setBrand(cr.getBrand());
	c.setModel(cr.getModel());
	return c;
	}
	
	public CarResponseDto mapToCarResponse(Car c) {
		
		CarResponseDto cr = new CarResponseDto();
		cr.setCarId(c.getCarId());
		cr.setBrand(c.getBrand());
		cr.setModel(c.getModel());
		
		return cr;
	}
}
