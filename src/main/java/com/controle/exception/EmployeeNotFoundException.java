package com.controle.exception;

public class EmployeeNotFoundException extends RequestException {

	private static final long serialVersionUID = 1L;


	public EmployeeNotFoundException(String employeeId) {
		super("FuncionárioNãoEncontrado", "Funcionário não encontrado" + employeeId);
	}
	
	
	
}
