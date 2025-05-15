package com.practice.csa.RequestDto;

import org.springframework.stereotype.Component;


public class CarRequestDto {

	private String brand;
	private String Model;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}

	
	
}
