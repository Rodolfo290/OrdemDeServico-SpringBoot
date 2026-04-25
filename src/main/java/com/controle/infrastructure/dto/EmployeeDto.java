package com.controle.infrastructure.dto;

import com.controle.entities.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//vai para o cliente, ele é o que o cliente vai ler de saída 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private  String name;
	private  String email;
	private String status;

	public static EmployeeDto create(Employee employee) {

		// 1. Criamos a variável vazia
	    String statusForma;

	    // 2. Usamos o if/else para decidir o valor
	    if (employee.isActive()) {
	        statusForma = "ATIVO";
	    } else {
	        statusForma = "DESATIVADO";
	    }

	    // 3. Passamos a variável preenchida para o construtor
	    return new EmployeeDto(
	        employee.getName().toUpperCase(),
	        employee.getEmail(),
	        statusForma
	    );

	}
//	public static EmployeeDto create(Employee employee) {
//		
//		return new EmployeeDto(employee.getId(), employee.getName());
//		
//	}

}
