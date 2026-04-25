package com.controle.infrastructure.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
//lado externo, quem informa é quem vai usar o sistema
@Data
public class SaveEmployeeDataDto {
	
	@NotNull(message = "Nome inválido:")
	@Size(min = 1, max  = 100)
	private String name;
	
	@NotNull(message = "Email inválido:")
	@Size(min = 1, max  = 100)
	private String email;
	

	
	


}
