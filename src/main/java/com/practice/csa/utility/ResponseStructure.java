package com.practice.csa.utility;

public class ResponseStructure <S>{
	
	
	private int statusCode;
	private String message;
	private S data;
	public int getStatusCode() {
		return statusCode;
	}
	public ResponseStructure<S> setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ResponseStructure<S> setMessage(String message) {
		this.message = message;
		return this;
	}
	public S getData() {
		return data;
	}
	public ResponseStructure<S> setData(S data) {
		this.data = data;
		return this;
	}

	
}
