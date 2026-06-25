package com.controle.exception;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends RequestException {

	private static final long serialVersionUID = 1L;


	public EmployeeNotFoundException(String employeeId) {
		super(HttpStatus.NOT_FOUND, "Funcionário-Não-Encontrado", "Funcionário não encontrado" + employeeId);
	}
	
	
	
}
