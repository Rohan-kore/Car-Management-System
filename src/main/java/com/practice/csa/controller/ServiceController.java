package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.RequestDto.ServiceRequestDto;
import com.practice.csa.ResponseDto.ServiceResponseDto;
import com.practice.csa.entity.CarService;
import com.practice.csa.service.ServiceService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/services")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(@RequestBody ServiceRequestDto service){
		System.out.println("in");
		return serviceService.addService(service);
	}
	

	@GetMapping("/services/{id}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> findServiceById(@PathVariable int id){
		return serviceService.findServiceById(id);
	}


	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/services/{id}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> updateServiceById(@PathVariable int id, @RequestBody  ServiceRequestDto updatedService){
		return serviceService.updateServiceById(id, updatedService);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/services/{id}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> deleteServiceById(@PathVariable   int id){
		return serviceService.deleteServiceById(id);
		
				
	}
	
	@GetMapping("/services")
	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAllService(){
		return serviceService.findAllService();
		}
}
