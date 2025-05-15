package com.practice.csa.serviceImpl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.practice.csa.Mapper.CarMapper;
import com.practice.csa.RequestDto.CarRequestDto;
import com.practice.csa.ResponseDto.CarResponseDto;
import com.practice.csa.entity.Car;
import com.practice.csa.exception.CarNotFoundById;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.service.CarServiceIn;
import com.practice.csa.utility.ResponseStructure;

@Service
public class CarServiceImpl implements CarServiceIn {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarMapper carMapper;

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> addcar(CarRequestDto carRe ){
//		return ResponseEntity.status(HttpStatus.CREATED)
//				.body(new ResponseStructure<CarResponseDto>()
//						.setStatusCode(HttpStatus.CREATED.value())
//						.setData(carMapper.mapToCarResponse(carRepository.save(carMapper.mapToCar(carRequestDto))))
//						.setMessage("Car Object Created Succesfully..."));
		
		Car car = carRepository.save(carMapper.mapToCar(carRe));
		CarResponseDto mapTCarResponseDto = carMapper.mapToCarResponse(car);
		ResponseStructure<CarResponseDto>rs = new ResponseStructure<CarResponseDto>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Created");
		rs.setData(mapTCarResponseDto);
		
		return new ResponseEntity<ResponseStructure<CarResponseDto>>(rs,HttpStatus.CREATED);
		
		

	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(int carId) {
		return  carRepository.findById(carId)
				.map(car ->ResponseEntity
						.status(HttpStatus.FOUND)
						.body(new ResponseStructure<CarResponseDto>()
								.setStatusCode(HttpStatus.FOUND.value())
								.setMessage("Car Object Found Succesfully..")
								.setData(carMapper.mapToCarResponse(car))))                                 //[Optional<ResponseStructure<CarResponse>>]
				.orElseThrow(() -> new CarNotFoundById ("Failed To Find Users "));


	}
	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> updateCarById(int carId, CarRequestDto updatedCar) {
		return carRepository.findById(carId).map(exCar -> {
			Car car = carMapper.mapToCar(updatedCar);
			car.setCarId(exCar.getCarId());


			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<CarResponseDto>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Car Object Updated Succesfully..")
							.setData(carMapper.mapToCarResponse(carRepository.save(car))));
		}).orElseThrow(()-> new CarNotFoundById("Car Id not Founds"));
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> deleteCarById(int carId) {
		return carRepository.findById(carId).map(car ->{
			carRepository.delete(car);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<CarResponseDto>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Car Object Deleted Succesfully..")
							.setData(carMapper.mapToCarResponse(car)));
		}).orElseThrow(()-> new CarNotFoundById("Car Id not Founds"));

	}

	@Override
	public ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCar() {
		List<CarResponseDto> response = carRepository.findAll().stream().map(car -> carMapper.mapToCarResponse(car)).toList();

		ResponseStructure<List<CarResponseDto>> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.FOUND.value());
		rs.setMessage("Car Object Found Succesfully");
		rs.setData(response);
		return new ResponseEntity<ResponseStructure<List<CarResponseDto>>>(rs,HttpStatus.FOUND);

	}





}
