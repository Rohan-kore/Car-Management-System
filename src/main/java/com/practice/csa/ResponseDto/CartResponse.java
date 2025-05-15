package com.practice.csa.ResponseDto;

import java.util.List;

public class CartResponse {
	
	private int id;
	
	List<ServiceResponseDto> service;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ServiceResponseDto> getService() {
		return service;
	}


	
	
	

}
