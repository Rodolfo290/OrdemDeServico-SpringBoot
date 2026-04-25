package com.controle.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	// Employee findByIdAndDeletedFalse = (String id);

	Optional<Employee> findByName(String name);

	Optional<Employee> findByEmail(String email);

	List<Employee> findAllByName(String name);

	List<Employee> findByNameContainingIgnoreCaseAndActiveTrue(String name);
	List<Employee> findByActiveFalse();
	List<Employee> findByActiveTrue();
//	List<Employee> findByServiceDate(LocalDate serviceDate);
//	List<Employee> findByCompanyName(String companyName);

	// List<Employee> findByName(String name);

}
