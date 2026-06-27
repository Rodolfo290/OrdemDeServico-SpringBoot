package com.controle.exception;

import org.springframework.http.HttpStatus;

public class BudgetNotFoundException extends RequestException {

	private static final long serialVersionUID = 1L;

	public BudgetNotFoundException(String budgetId) {
		super(HttpStatus.NOT_FOUND, "Orçamento-Não-Encontrado", " Orçamento Não Encontrado" + budgetId);
	}
	
}
