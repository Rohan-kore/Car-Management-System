package com.practice.csa.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.practice.csa.Mapper.BookingMapper;
import com.practice.csa.ResponseDto.BookingResponse;
import com.practice.csa.entity.Booking;
import com.practice.csa.entity.Car;
import com.practice.csa.entity.CarService;
import com.practice.csa.entity.Cart;
import com.practice.csa.entity.Contract;
import com.practice.csa.entity.User;
import com.practice.csa.exception.CarNotFoundById;
import com.practice.csa.exception.UserNotFoundByIdException;
import com.practice.csa.repository.BookingRepository;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.repository.ContractRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.service.BookingService;
import com.practice.csa.utility.ResponseStructure;

public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private BookingMapper bookingMapper;
	@Autowired
	private ContractRepository contractRepository;

	@Override
	public ResponseEntity<ResponseStructure<BookingResponse>> createBooking(int id) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User customer = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundByIdException("USer Not Found.."));


		Car car = carRepository.findById(id).orElseThrow(()-> new CarNotFoundById("Car Id Not Found By Id"));

		Booking booking = new Booking();
		booking.setCar(car);;
		booking.setCustomer(customer);

		Booking saveBooking = bookingRepository.save(booking);
		Cart cart = customer.getCart();
		List<CarService> services =cart.getCarService();

		List<Contract> contractList = services.stream().map(service  -> {
			Contract contract = new Contract();
			contract.setCarService(service);
			contract.setBooking(booking);
			return contractRepository.save(contract);
		}).toList();

		booking.setContracts(contractList);

		bookingRepository.save(booking);
		BookingResponse bookingResponse = bookingMapper.mapToBookingResponse(saveBooking);		
		return	 ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<BookingResponse>()
						.setStatusCode(HttpStatus.CREATED.value())
						.setMessage("Booking Created Succesfully")
						.setData(bookingResponse));


	}



}
