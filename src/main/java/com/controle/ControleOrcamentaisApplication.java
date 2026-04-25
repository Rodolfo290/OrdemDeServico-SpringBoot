package com.controle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestão de Ordens de Serviço",
        version = "1.0",
        description = "Sistema desenvolvido para eliminar o uso de papel na oficina, gerenciando orçamentos e funcionários."
    )
)
//@SpringBootApplication
public class ControleOrcamentaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleOrcamentaisApplication.class, args);
	}

}
