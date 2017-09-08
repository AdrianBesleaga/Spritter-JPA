package com.cgm.spriTTer.dto;

public class ServiceResponse {

	public ServiceResponse(String message) {
		super();
		this.message = message;
	}

	public ServiceResponse(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

	private String message = "Operation Succesfull!";
	private int code = 200;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
