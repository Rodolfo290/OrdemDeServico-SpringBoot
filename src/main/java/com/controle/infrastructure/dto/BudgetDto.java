package com.controle.infrastructure.dto;

import java.time.LocalDate;

import com.controle.entities.Budget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// vai para o cliente, ele é o que o cliente vai ler de saída 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDto {

	// private final String id;
	private String employeeName;
	private String companyName;
	private String location;
	private String vehiclePlate;
	private LocalDate serviceDate;
	private String descriptionService;
	private String status;

	public static BudgetDto create(Budget budget) {
		// Buscamos o nome do funcionário através da associação
		String nameEmployee = budget.getEmployee().getName();

//		return new BudgetDto(budget.getId(), nameEmployee.toUpperCase(), budget.getCompanyName(), budget.getLocation(),
//				budget.getVehiclePlate(), budget.getServiceDate(), budget.getDescriptionService(),
//				budget.getStatus().name());

		return new BudgetDto(nameEmployee, budget.getCompanyName(), budget.getLocation(),
				budget.getVehiclePlate(), budget.getServiceDate(), budget.getDescriptionService(),
				budget.getStatus().name());
	}

}
