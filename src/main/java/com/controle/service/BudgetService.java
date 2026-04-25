package com.controle.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.controle.entities.Budget;
import com.controle.entities.Employee;
import com.controle.exception.BudgetNotFoundException;
import com.controle.exception.EmployeeNotFoundException;
import com.controle.infrastructure.dto.BudgetDto;
import com.controle.infrastructure.dto.SaveBudgetDataDto;
import com.controle.model.BudgetStatus;
import com.controle.repository.BudgetRepository;
import com.controle.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BudgetService {

	private final BudgetRepository repository;
	private final EmployeeRepository employeeRepository;

	@Transactional
	public Budget createBudget(SaveBudgetDataDto dto) {

		String employeeId = dto.getEmployeeId();

		Employee emp = employeeRepository.findByName(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException(employeeId + " não foi encontrado:"));
		if (!emp.isActive()) {
			throw new IllegalStateException("Funcionário inativo ou não existente não pode criar orçamento:");
		}
		Budget budget = Budget.builder().companyName(dto.getCompanyName()).location(dto.getLocation())
				.vehiclePlate(dto.getVehiclePlate()).serviceDate(dto.getServiceDate())
				.descriptionService(dto.getDescriptionService()).status(BudgetStatus.CREATED).employee(emp).build();

		log.info("Orçamento criado com sucesso:");
		return repository.save(budget);

	}

	@Transactional(readOnly = true)
	public Budget loadBudget(String budgetId) {
		return repository.findById(budgetId).orElseThrow(() -> new BudgetNotFoundException(budgetId));
	}

	@Transactional
	public Budget updateBudget(String id, SaveBudgetDataDto dto) {

		Budget budget = loadBudget(id);

		budget.setCompanyName(dto.getCompanyName());
		budget.setLocation(dto.getLocation());
		budget.setVehiclePlate(dto.getVehiclePlate());
		budget.setServiceDate(dto.getServiceDate());
		budget.setDescriptionService(dto.getDescriptionService());

		return budget;
	}
	
	//deleta tudo do banco
	@Transactional
	public void deleteBudget(String id) {
		Budget budget = loadBudget(id);
		repository.delete(budget);
	}

	@Transactional(readOnly = true)
	public List<BudgetDto> listAll() {
		List<Budget> budgets = repository.findAll();
		List<BudgetDto> dtos = budgets.stream()
				.sorted(Comparator.comparing(Budget::getCompanyName, String.CASE_INSENSITIVE_ORDER))
				.map(BudgetDto::create).toList();

		log.info("Lista de todos orçamentos:");
		return dtos;
	}

	@Transactional
	public void aproveBudget(String id) {

		Budget budget = repository.findById(id).orElseThrow(() -> new RuntimeException("Orçamento não encontrado:"));
		if (budget.getStatus() != BudgetStatus.CREATED) {
			throw new IllegalStateException("Apenas orçamentos abertos podem ser aprovados:");
		}

		budget.setStatus(BudgetStatus.APPROVED);
	}

	@Transactional(readOnly = true)
	public List<BudgetDto> findByEmployeeName(String name) {
		List<Budget> budgets = repository.findByEmployeeName(name);
		List<BudgetDto> dtos = budgets.stream().map(BudgetDto::create).toList();
		
		if (dtos.isEmpty()) {
			log.info("Funcionário não existe: {}", name);
			return dtos;
		}
		
		log.info("Orçamento funcionário: {}", name);
		return dtos;
	}

	@Transactional(readOnly = true)
	public List<BudgetDto> findByServiceDate(LocalDate serviceDate) {

		List<Budget> budgets = repository.findByServiceDate(serviceDate);
		List<BudgetDto> dtos = budgets.stream().map(BudgetDto::create).toList();
		log.info("Lista por data:");
		return dtos;
	}
//	public List<Budget> findByServiceDate(LocalDate serviceDate) {
//		
//		return repository.findByServiceDate(serviceDate);
//	}

//	@Transactional(readOnly = true)
//	public List<Budget> FindByCompanyName(String Companyname) {
//		return repository.findByCompanyName(Companyname);
//	}

	// esse foi
	@Transactional(readOnly = true)
	public List<BudgetDto> findByCompanyName(String companyName) {

		List<Budget> budgets = repository.findByCompanyName(companyName);
		List<BudgetDto> dtos = budgets.stream().sorted(Comparator.comparing(Budget::getCompanyName))
				.map(BudgetDto::create).toList();
		if (dtos.isEmpty()) {
			log.info("Funcionário não existe: {}", companyName);
			return dtos;
		}
		
		log.info("Orçamento pelo nome da empresa:");
		return dtos;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
