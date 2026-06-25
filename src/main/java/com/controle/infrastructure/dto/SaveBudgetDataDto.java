package com.controle.infrastructure.dto;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

//lado externo, quem informa é quem vai usar o sistema
@Data
public class SaveBudgetDataDto {

	
	
	@NotBlank
	@Size(min = 1, max  = 50)
	private String employeeId;
	
	// TODO: Futuro Upgrade - Permitir múltiplos colaboradores por orçamento
    // private Set<String> assistantIds;
	
	@NotBlank
	@Size(min = 1, max  = 50)
	private String companyName;

	@NotBlank
	@Size(min = 1, max = 50)
	private String location;
	
	@NotBlank
	@Size(max  = 10)
	private String vehiclePlate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Data incorreta:")
	@PastOrPresent(message = "A data não pode ser futura:")
  //@FutureOrPresent(message = "A data deve ser atual ou futura:")
	private LocalDate serviceDate;
	
    @NotBlank
    @Size(min = 1, max  = 500)
	private String descriptionService;
	

}
