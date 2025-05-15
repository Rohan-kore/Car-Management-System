package com.practice.csa.utility;

public class ErrorStructure <S>{
	
	private int statuscode;
	private String errorMessage;
	private S data;
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public S getData() {
		return data;
	}
	public void setData(S data) {
		this.data = data;
	}
	

}
