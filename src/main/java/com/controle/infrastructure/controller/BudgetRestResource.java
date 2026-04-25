package com.controle.infrastructure.controller;

import static com.controle.infrastructure.controller.RestConstants.PATH_BUDGET;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
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
	public ResponseEntity<List<BudgetDto>> listAll() {

	//	List<Budget> dtos = service.listAll();

		return ResponseEntity.ok(service.listAll());
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

	public ResponseEntity<List<BudgetDto>> findByEmployeeName(@RequestParam String employeeName) {

		// return service.findByEmployeeName(employeeName);
		////////////////////////////////////////////////
		// service.findByEmployeeName(employeeName);

		// return ResponseEntity.badRequest().build();
		///////////////////////////////////////////////////////////////////
		// List<Budget> budgets = service.findByEmployeeName(employeeName);

		// List<BudgetDto> dtos = new ArrayList<>();

		// for (Budget budget : budgets) {
		// dtos.add(BudgetDto.create(budget));
		// }

		// return ResponseEntity.ok(dtos);
		//////////////////////////////////////////////////////////////////////

		//List<BudgetDto> dtos = service.findByEmployeeName(employeeName);

		return ResponseEntity.ok(service.findByEmployeeName(employeeName));

	}

	@GetMapping("/date")
	public ResponseEntity<List<BudgetDto>> findByServiceDate(@RequestParam LocalDate date) {

//		service.findByDate(date);
//
//		return ResponseEntity.badRequest().build();
//
//		List<Budget> budgets = service.findByServiceDate(date);
//		
//		List<BudgetDto> dtos = budgets.stream().map(BudgetDto::create).toList();
//		return ResponseEntity.ok(dtos);
		//List<BudgetDto> dtos = service.findByServiceDate(date);
		return ResponseEntity.ok(service.findByServiceDate(date));
	}

	// @GetMapping("/company/{companyName}")
	@GetMapping("/company")
	public ResponseEntity<List<BudgetDto>> findByCompanyName(@RequestParam String companyName) {
		return ResponseEntity.ok(service.findByCompanyName(companyName));
	}

}
