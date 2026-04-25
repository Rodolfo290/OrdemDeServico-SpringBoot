package com.controle.exception;

public class BudgetNotFoundException extends RequestException {

	private static final long serialVersionUID = 1L;

	public BudgetNotFoundException(String budgetId) {
		super("OrçamentoNãoEncontrado", "Orçamento Não Encontrado" + budgetId);
	}
}
