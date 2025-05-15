package com.practice.csa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.csa.ResponseDto.BookingResponse;
import com.practice.csa.service.BookingService;
import com.practice.csa.utility.ResponseStructure;

public class BookingController {
	
	@Autowired
	private BookingService  bookingService;
	
	@PostMapping("/cars/{carId}/bookings")
	public ResponseEntity<ResponseStructure<BookingResponse>> createBooking(@PathVariable int id){
		return bookingService.createBooking(id);
	}
	
	

}
