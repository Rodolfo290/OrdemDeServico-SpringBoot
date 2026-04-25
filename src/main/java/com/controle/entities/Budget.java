package com.controle.entities;

import java.time.LocalDate;

import com.controle.model.BudgetStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//(Orçamento)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Budget")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false, length = 36)
	private String id;

	@Column(name = "companyName", nullable = false, length = 50)
	private String companyName;

	@Column(name = "location", nullable = false, length = 50)
	private String location;

	@Column(name = "vehiclePlate", nullable = false, length = 20)
	private String vehiclePlate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "serviceDate", nullable = false, length = 10)
	private LocalDate serviceDate;

	@Column(name = "descriptionService", nullable = false, length = 500)
	private String descriptionService;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private BudgetStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", nullable = false) // apontando para o funcionario
	private Employee employee;

	
	
	
}
