package com.controle.exception;

import org.springframework.http.HttpStatus;

public class BudgetEmployeeNotFoundException extends RequestException {

	private static final long serialVersionUID = 1L;
	
	public BudgetEmployeeNotFoundException(String name) {
        super(HttpStatus.NOT_FOUND, "EmployeeNotFoundException:", name + " Funcionário não existe:" );
    }

}
