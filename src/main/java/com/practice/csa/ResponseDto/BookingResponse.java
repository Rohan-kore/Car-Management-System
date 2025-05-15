package com.practice.csa.ResponseDto;

import java.util.List;

public class BookingResponse {
	private int id;
	
	private CarResponseDto carResponseDto;
	
	private List<ServiceResponseDto>service;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CarResponseDto getCarResponseDto() {
		return carResponseDto;
	}
	public void setCarResponseDto(CarResponseDto carResponseDto) {
		this.carResponseDto = carResponseDto;
	}
	public List<ServiceResponseDto> getService() {
		return service;
	}
	public void setService(List<ServiceResponseDto> service) {
		this.service = service;
	}
	
	
}
