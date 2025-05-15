package com.practice.csa.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.practice.csa.exception.CarNotFoundById;
import com.practice.csa.exception.ServiceNotFoundException;

@RestControllerAdvice
public class ApplicationHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> carNotFoundById(CarNotFoundById ex){
		 ErrorStructure<String> es= new ErrorStructure<String>();
		 es.setStatuscode(HttpStatus.NOT_FOUND.value());
		 es.setErrorMessage(ex.getMessage());
		 es.setData("Car with the given Id is Not Present In The DataBase");
		 
		 return new ResponseEntity<ErrorStructure<String>>(es,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> serviceNotFoundById(ServiceNotFoundException ex){
		 ErrorStructure<String> es1= new ErrorStructure<String>();
		 es1.setStatuscode(HttpStatus.NOT_FOUND.value());
		 es1.setErrorMessage(ex.getMessage());
		 es1.setData("Service with the given Id is Not Present In The DataBase");
		 
		 return new ResponseEntity<ErrorStructure<String>>(es1,HttpStatus.NOT_FOUND);
	}
	
}
