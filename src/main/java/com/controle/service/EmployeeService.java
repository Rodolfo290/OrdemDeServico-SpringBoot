package com.controle.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controle.entities.Employee;
import com.controle.exception.EmployeeEmailConflictException;
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
			throw new EmployeeEmailConflictException("O e-mail " + dto.getEmail() + " já está cadastrado.");
		});

		// LINHA 2: A Construção, // Forçamos que todo novo funcionário nasça ativo com
		// o active(true0
		Employee employee = Employee.builder().name(dto.getName()).email(dto.getEmail()).active(true).build();
		log.info("Criando Funcionario {}", employee);
		return repository.save(employee);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// 
/// 
	@Transactional(readOnly = true)
	public Employee loadEmployee(String employeeId) {
		return repository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Transactional(readOnly = false)
	public Employee updateEmployee(String id, SaveEmployeeDataDto dto) {
		// 1. Busca o funcionário que estamos tentando editar
		Employee employee = loadEmployee(id);

		// 2. Validação Inteligente de E-mail
		repository.findByEmail(dto.getEmail()).ifPresent(e -> {
			if (!e.getId().equalsIgnoreCase(id)) {
				throw new EmployeeEmailConflictException("O e-mail " + dto.getEmail() + " já está cadastrado.");
			}
		});
		employee.setName(dto.getName());
		employee.setEmail(dto.getEmail());
		log.info("Atualização realizado com sucesso:");
		return employee;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	// deleta totalmente
	@Transactional(readOnly = false)
	public void deleteAllEmployee(String id) {
		Employee employee = loadEmployee(id);
		repository.delete(employee);
		log.info("Deletado com sucesso:");

	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	@Transactional(readOnly = false)
	public void deleteEmployee(String id) {
		Employee employee = loadEmployee(id);
		employee.setActive(false);
		// repository.delete(employee);
		log.info("Soft-Delete:\n" + employee.getName() + "\n" + employee.getEmail());

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional(readOnly = false)
	public void deactivateEmployee(String id) {
		Employee emp = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Funcionário não encontrado:"));

		emp.setActive(false);
		log.info("Funcionário inativado com sucesso (Soft Delete): " + emp.getName() + " - " + id);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// 
/// 
	@Transactional(readOnly = true) // Como é só listagem = true
	public Page<EmployeeDto> findByActiveFalse(Pageable page) {
		// 1. Buscamos no banco apenas quem está com active = false
		Page<Employee> employee = repository.findByActiveFalse(page);
		log.info("Busca realizada com sucesso para funcionário: ");
		return employee.map(EmployeeDto::create);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	@Transactional(readOnly = true)
	public Page<EmployeeDto> findByActiveTrue(Pageable page) {

		Page<Employee> employee = repository.findByActiveTrue(page);
		log.info("Busca realizada com sucesso para funcionário: ");
		return employee.map(EmployeeDto::create);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Transactional(readOnly = true)
	public Page<EmployeeDto> loadEmployeeActive(String name, Pageable page) {
		if (name != null && !name.trim().isEmpty()) {
			Page<Employee> employee = repository.findByNameContainingIgnoreCaseAndActiveTrue(name, page);
			log.info("Busca realizada com sucesso para funcionários: ");
			return employee.map(EmployeeDto::create);
		}
		
		Page<Employee> employee = repository.findByActiveTrue(page);
		log.info("Busca realizada com sucesso para funcionários: ");
		return employee.map(EmployeeDto::create);

	}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@Transactional(readOnly = true) // Melhor para performance em buscas
	public Page<EmployeeDto> findAll(Pageable page) {
		Page<Employee> employee = repository.findAll(page);
		return employee.map(EmployeeDto::create);
	}

	@Transactional(readOnly = true)
	public Page<EmployeeDto> findByNameContainingIgnoreCase(String name, Pageable page) {

		if (name != null && !name.trim().isEmpty()) {
			Page<Employee> employee = repository.findByNameContainingIgnoreCase(name, page);
	        log.info("Busca realizada com sucesso para funcionário: " + name);
			return employee.map(EmployeeDto::create);
		}
		
		throw new EmployeeNotFoundException(name);

	}

}
