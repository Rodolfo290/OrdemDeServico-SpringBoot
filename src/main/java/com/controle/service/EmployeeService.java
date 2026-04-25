package com.controle.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controle.entities.Employee;
import com.controle.exception.EmployeeNotFoundException;
import com.controle.infrastructure.dto.EmployeeDto;
import com.controle.infrastructure.dto.SaveEmployeeDataDto;
import com.controle.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

	private final EmployeeRepository repository;

	@Transactional(readOnly = false)
	public Employee createEmployee(SaveEmployeeDataDto dto) {
		// LINHA 1: A Validação de email
		repository.findByEmail(dto.getEmail()).ifPresent(e -> {
			throw new IllegalStateException("O e-mail " + dto.getEmail() + " já está cadastrado.");
		});

		// LINHA 2: A Construção, // Forçamos que todo novo funcionário nasça ativo com
		// o active(true0
		Employee employee = Employee.builder().name(dto.getName()).email(dto.getEmail()).active(true).build();
		log.info("Criando Funcionario {}", employee);
		return repository.save(employee);
	}

	@Transactional(readOnly = false)
	public Employee loadEmployee(String employeeId) {
		return repository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
	}

	
	@Transactional(readOnly = false)
	public Employee updateEmployee(String id, SaveEmployeeDataDto dto) {
		// 1. Busca o funcionário que estamos tentando editar
		Employee employee = loadEmployee(id);
		
		// 2. Validação Inteligente de E-mail
		repository.findByEmail(dto.getEmail()).ifPresent(e -> {
			if (!e.getEmail().equalsIgnoreCase(id)) {
				throw new IllegalStateException("O e-mail " + dto.getEmail() + " já está cadastrado.");
			}
		});
		employee.setName(dto.getName());
		employee.setEmail(dto.getEmail());
		log.info("Atualização realizado com sucesso:");
		return employee;
	}

	//deleta totalmente
	@Transactional(readOnly = false)
	public void deleteAllEmployee(String id) {
		Employee employee = loadEmployee(id);
		 repository.delete(employee);
		log.info("Deletado com sucesso:");

	}
	
	@Transactional(readOnly = false)
	public void deleteEmployee(String id) {
		Employee employee = loadEmployee(id);
		employee.setActive(false);
		// repository.delete(employee);
		log.info("Soft-Delete:\n" + employee.getName() + "\n" + employee.getEmail());

	}
//	
//	@Transactional(readOnly = false)
//	public void deleteEmployee(String id) {
//		Employee employee = loadEmployee(id);
//		
//		
//		// Removemos o repository.delete(employee);
//	    // E apenas mudamos o status dele:
//		
//		employee.setActive(false);
//		//repository.save(employee):
//		log.info("Funcionário inativado com sucesso (Soft Delete): " + employee.getName() + " - " + id);
//	}

	@Transactional(readOnly = false)
	public void deactivateEmployee(String id) {
		Employee emp = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado:"));

		emp.setActive(false);
		log.info("Funcionário inativado com sucesso (Soft Delete): " + emp.getName() + " - " + id);
	}

	@Transactional(readOnly = true) // Como é só listagem, readOnly aumenta a performance
	public List<EmployeeDto> findByActiveFalse() {
		// 1. Buscamos no banco apenas quem está com active = false
		List<Employee> employee = repository.findByActiveFalse();

		List<EmployeeDto> dtos = employee.stream()
				.sorted(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER)).map(EmployeeDto::create)
				.toList();
		return dtos;
	}

	@Transactional(readOnly = true)
	public List<EmployeeDto> findByActiveTrue() {
		List<Employee> employee = repository.findByActiveTrue();
		List<EmployeeDto> dtos = employee.stream()
				.sorted(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER)).map(EmployeeDto::create)
				.toList();
		return dtos;
	}

	@Transactional(readOnly = false)
	public List<EmployeeDto> loadEmployeeActive(String name) {
		List<Employee> emp = repository.findByNameContainingIgnoreCaseAndActiveTrue(name);

		return emp.stream().map(EmployeeDto::create).collect(Collectors.toList());
	}
//	public List<EmployeeDto> loadEmployeeActive(String name) {
////	    // Busca no banco usando a regra que definimos
////	    List<Employee> listEmployee = repository.findByNameContainingIgnoreCaseAndActiveTrue(name);
////
////	    // Converte a lista de entidades para uma lista de DTOs
////	    return listEmployee.stream()
////	            .map(EmployeeDto::create)
////	            .collect(Collectors.toList());
//		
//		
//	}

//	@Transactional(readOnly = false)
//	public List<Employee> listAll() {
//		return repository.findAll();
//	}

	@Transactional(readOnly = true) // Melhor para performance em buscas
	public List<EmployeeDto> listAll() {
		return repository.findAll().stream()
				.sorted(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER)).map(EmployeeDto::create)
				.toList(); // No Java 17+ pode usar .toList() direto
	}

	@Transactional(readOnly = false)
	public List<EmployeeDto> findAllByName(String name) {

		List<Employee> emp = repository.findAllByName(name);
		List<EmployeeDto> dtos = emp.stream().map(EmployeeDto::create).toList();
		
		if (dtos.isEmpty()) {
			log.info("Funcionário não existe = " + name);
			return dtos;
		}
		log.info("Busca realizada com sucesso para funcionário: " + name);
		return dtos;

	}

}
