package com.controle.exception;

import org.springframework.http.HttpStatus;

public class EmployeeEmailConflictException extends RequestException{

	private static final long serialVersionUID = 1L;

	public EmployeeEmailConflictException(String email) {
		super(HttpStatus.CONFLICT, "Email-Ja-Cadastro", "O e-mail " + email + " já está cadastrado no sistema para outro funcionário.");
	}
}
