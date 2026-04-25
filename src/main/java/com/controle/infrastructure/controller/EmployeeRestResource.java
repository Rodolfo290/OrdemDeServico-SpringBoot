package com.controle.infrastructure.controller;

import static com.controle.infrastructure.controller.RestConstants.PATH_EMPLOYEE;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controle.entities.Employee;
import com.controle.infrastructure.dto.EmployeeDto;
import com.controle.infrastructure.dto.SaveEmployeeDataDto;
import com.controle.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PATH_EMPLOYEE)
@RequiredArgsConstructor
public class EmployeeRestResource {

	private final EmployeeService service;

	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid SaveEmployeeDataDto dto) {
		Employee emp = service.createEmployee(dto);
		return ResponseEntity.created(URI.create(PATH_EMPLOYEE + "/" + emp.getId())).body(EmployeeDto.create(emp));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> loadEmployee(@PathVariable String id) {

		Employee emp = service.loadEmployee(id);
		return ResponseEntity.ok(EmployeeDto.create(emp));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String id,
			@RequestBody @Valid SaveEmployeeDataDto dto) {

		Employee emp = service.updateEmployee(id, dto);

		return ResponseEntity.ok(EmployeeDto.create(emp));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable String id) {

		service.deleteAllEmployee(id);

		return ResponseEntity.noContent().build();

	}
	
	@PutMapping("/{id}/deactivate")
	public ResponseEntity<EmployeeDto> deactivateEmployee(@PathVariable String id){
		
		service.deactivateEmployee(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
//	@GetMapping
//	public ResponseEntity<List<EmployeeDto>> listAll(){
//		List<Employee> employees = service.listAll();
//		List<EmployeeDto> dtos = employees.stream().sorted(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER)).map(EmployeeDto::create).toList();
//		return ResponseEntity.ok(dtos);
//		
//	}
//	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> listAll() {
	    // Ele só pede e entrega. Simples assim.
	    return ResponseEntity.ok(service.listAll());
	}
	
	
	
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDto>> findByEmployeeName(@RequestParam String name){
		
		
		
//		List<Employee> emplyoee = service.findByEmployeeName(employeeName);
//		List<EmployeeDto> dtos = emplyoee.stream().map(EmployeeDto::create).toList();
		return ResponseEntity.ok(service.findAllByName(name));
		
	}
	

	@GetMapping("/{name}/employee")
	public ResponseEntity<List<EmployeeDto>> loadEmployeeActive(@PathVariable String name){
//		service.loadEmployeeActive(name);
//		return ResponseEntity.noContent().build();
		return ResponseEntity.ok(service.loadEmployeeActive(name));
	}
	
	@GetMapping("/activefalse")
	public ResponseEntity<List<EmployeeDto>> findByActiveFalse(){
		return ResponseEntity.ok(service.findByActiveFalse());
	}
	
	
	@GetMapping("/activetrue")
	public ResponseEntity<List<EmployeeDto>> findByActiveTrue(){
		return ResponseEntity.ok(service.findByActiveTrue());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
