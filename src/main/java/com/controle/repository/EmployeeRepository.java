package com.controle.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	// Employee findByIdAndDeletedFalse = (String id);
	Page<Employee> findAll(Pageable page);

	Optional<Employee> findByName(String name);
	Page<Employee> findByNameContainingIgnoreCase(String name ,Pageable page);

	Optional<Employee> findByEmail(String email);

	List<Employee> findAllByName(String name);

	List<Employee> findByNameContainingIgnoreCaseAndActiveTrue(String name);
	Page<Employee> findByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable page);

	List<Employee> findByActiveFalse();
	Page<Employee>findByActiveFalse(Pageable page);

	List<Employee> findByActiveTrue();
	Page<Employee> findByActiveTrue(Pageable page);
//	List<Employee> findByServiceDate(LocalDate serviceDate);
//	List<Employee> findByCompanyName(String companyName);

	// List<Employee> findByName(String name);

}
