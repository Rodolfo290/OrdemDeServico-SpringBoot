package com.controle.exception;

import org.springframework.http.HttpStatus;

public class BudgetCompanyNameBadRequestException extends RequestException {

	private static final long serialVersionUID = 1L;

	public BudgetCompanyNameBadRequestException(String companyName) {
			super(HttpStatus.BAD_REQUEST, "Nenhuma-Ordem-Serviço-Para-Empresa", companyName + " = Não encontrado Ordem De Serviço para o nome da empresa ou nada digitado no campo nome da empresa");	
}
}
