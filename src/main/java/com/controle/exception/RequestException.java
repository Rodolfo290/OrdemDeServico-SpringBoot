package com.controle.exception;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final String errorCode;

	public RequestException(String message, String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
