package com.controle.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

public class BudgetDateBadRequestException extends RequestException{

	private static final long serialVersionUID = 1L;

	public BudgetDateBadRequestException(LocalDate serviceDate) {
		super(HttpStatus.BAD_REQUEST,"Data-Incorreta", serviceDate + " A data não pode ser futura:"); 
	}
	
}
