package com.controle.exception;

import org.springframework.http.HttpStatus;

public class BudgetCompanyNameNotFoundException extends RequestException{

	private static final long serialVersionUID = 1L;

	public BudgetCompanyNameNotFoundException(String companyName) {
		super(HttpStatus.NOT_FOUND, "Nenhuma-Ordem-Serviço-Para-Empresa", companyName + " = Não encontrado Ordem De Serviço para o nome da empresa ou nada digitado no campo nome da empresa");
	}
}
