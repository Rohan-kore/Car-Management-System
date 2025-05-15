package com.practice.csa.service;

import org.springframework.http.ResponseEntity;

import com.practice.csa.ResponseDto.BookingResponse;
import com.practice.csa.utility.ResponseStructure;

public interface BookingService {
	
	ResponseEntity<ResponseStructure<BookingResponse>> createBooking(int id);
	

}
