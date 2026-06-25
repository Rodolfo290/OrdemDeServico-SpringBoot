package com.controle.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final String errorCode;
	private final HttpStatus status;

	public RequestException(HttpStatus status, String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
