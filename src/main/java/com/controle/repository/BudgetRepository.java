package com.controle.repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.entities.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {
	
//	Optional<Employee> findByName(String name);
	
	List<Budget> findByEmployeeName(String name);
	Page<Budget> findByEmployeeName(String name, Pageable page);
	
	List<Budget> findByServiceDate(LocalDate serviceDate);
	Page<Budget>findByServiceDate(LocalDate serviceDate, Pageable page);

	
	
	List<Budget> findByCompanyName(String companyName);
	//List<Budget> listAll();

}
