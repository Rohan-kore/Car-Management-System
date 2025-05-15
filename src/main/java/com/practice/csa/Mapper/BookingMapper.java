package com.practice.csa.Mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.csa.ResponseDto.BookingResponse;
import com.practice.csa.ResponseDto.CarResponseDto;
import com.practice.csa.ResponseDto.ServiceResponseDto;
import com.practice.csa.entity.Booking;
import com.practice.csa.entity.CarService;
import com.practice.csa.entity.Contract;

@Component
public class BookingMapper {

	@Autowired
	private CarMapper carMapper;

	@Autowired
	private ServiceMapper serviceMapper;

	public BookingResponse mapToBookingResponse(Booking booking) {
		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setId(booking.getId());
		bookingResponse.setCarResponseDto(carMapper.mapToCarResponse(booking.getCar()));
		
		List<Contract> contracts=booking.getContracts();
		
		
	 	List<ServiceResponseDto> list = contracts.stream().map(contract -> {
	 		CarService carService = contract.getCarService();
	 		ServiceResponseDto serviceResponseDto= serviceMapper.mapToServiceReponse(carService);
	 		return serviceResponseDto;
	 		
	 	}).toList();
	 	bookingResponse.setService(list);
			
			return bookingResponse;
		
		
	}
}
