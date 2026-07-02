package com.controle.infrastructure.controller;

import static com.controle.infrastructure.controller.RestConstants.PATH_BUDGET;

import org.springframework.data.domain.Pageable;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.controle.entities.Budget;
import com.controle.infrastructure.dto.BudgetDto;
import com.controle.infrastructure.dto.SaveBudgetDataDto;
import com.controle.service.BudgetService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PATH_BUDGET)
@RequiredArgsConstructor
public class BudgetRestResource {

	private final BudgetService service;

	@PostMapping
	public ResponseEntity<BudgetDto> createBudget(@RequestBody @Valid SaveBudgetDataDto dto) {

		Budget budget = service.createBudget(dto);

		return ResponseEntity.created(URI.create(PATH_BUDGET + "/" + budget.getId())).body(BudgetDto.create(budget));

	}

	@GetMapping("/{id}")
	public ResponseEntity<BudgetDto> loadBudget(@PathVariable String id) {

		Budget budget = service.loadBudget(id);

		return ResponseEntity.ok(BudgetDto.create(budget));
//		List<Budget> budgets = service.loadBudget(id);
//		List<BudgetDto> dtos = budgets.stream()
//			    .sorted(Comparator.comparing(b -> b.get) )
//			    .map(BudgetDto::create)
//			    .toList();
		// return ResponseEntity.ok(dtos);

	}

	@PutMapping("/{id}")
	public ResponseEntity<BudgetDto> updateBudget(@PathVariable String id, @RequestBody @Valid SaveBudgetDataDto dto) {

		Budget budget = service.updateBudget(id, dto);

		return ResponseEntity.ok(BudgetDto.create(budget));

	}

	@DeleteMapping("/{id}")
	public void deleteBudget(@PathVariable String id) {
		service.deleteBudget(id);
	}

	@GetMapping
	public ResponseEntity<Page<BudgetDto>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

		Pageable config = PageRequest.of(page, size, Sort.by("companyName").ascending());

		return ResponseEntity.ok(service.findAll(config));
		// List<BudgetDto> dtos = budgets.stream().sorted(Comparator.comparing(b ->
		// b.getEmployee().getName(),
		// String.CASE_INSENSITIVE_ORDER)).map(BudgetDto::create).toList();
		// List<BudgetDto> dtos = budgets.stream().sorted(Comparator.comparing(b ->
		// b.getEmployee().getName())).map(BudgetDto::create).toList();
		// List<BudgetDto> dtos = budgets.stream().sorted((o1, o2) ->
		// o1.getEmployee().getName().compareTo(o2.getEmployee().getName())
		// ).map(BudgetDto::create).toList();
	}

	// @GetMapping("/employee/{name}")
	@GetMapping("/employee")

	public ResponseEntity<Page<BudgetDto>> findByEmployeeName(@RequestParam String employeeName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

		Pageable config = PageRequest.of(page, size, Sort.by("employee.name").ascending());

		return ResponseEntity.ok(service.findByEmployeeName(employeeName, config));

	}

	@GetMapping("/date")
	public ResponseEntity<Page<BudgetDto>> findByServiceDate(@RequestParam LocalDate date,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

		Pageable config = PageRequest.of(page, size, Sort.by("serviceDate").ascending());
		return ResponseEntity.ok(service.findByServiceDate(date, config));
	}

	// @GetMapping("/company/{companyName}")
	@GetMapping("/company")
	public ResponseEntity<Page<BudgetDto>> findByCompanyName(@RequestParam String companyName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
	
		Pageable config = PageRequest.of(page, size, Sort.by("companyName").ascending());
		
		return ResponseEntity.ok(service.findByCompanyName(companyName, config));
	}

}
