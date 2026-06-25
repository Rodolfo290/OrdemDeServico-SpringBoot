package com.controle.exception;

public class BudgetEmployeeNotFoundException extends RequestException {

	private static final long serialVersionUID = 1L;
	
	public BudgetEmployeeNotFoundException(String name) {
        super("EmployeeNotFoundException:", name + " Funcionário não existe:" );
    }

}
