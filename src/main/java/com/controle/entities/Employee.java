package com.controle.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//funcionario
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false, length = 36)
	private String id;
	
	@Column(name = "employeeName", nullable = false, length = 80)
	private String name;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@Builder.Default
	@Column(name = "active", nullable = false)
	private boolean active = true;


	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	private List<Budget> budgets = new ArrayList<>();
	
	 
	
}
