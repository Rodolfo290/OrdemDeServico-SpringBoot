package com.controle.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.entities.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {
	
//	Optional<Employee> findByName(String name);
	
	List<Budget> findByEmployeeName(String name);
	List<Budget> findByServiceDate(LocalDate serviceDate);
	List<Budget> findByCompanyName(String companyName);
	//List<Budget> listAll();

}
