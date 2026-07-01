package com.controle.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControlAdvice {

//	@ExceptionHandler(RequestException.class)
//	public ResponseEntity<ListError> notFound(RequestException e, HttpServletRequest request){
//		ListError err = new ListError();
//		err.setTimestamp(Instant.now());
//        err.setStatus(HttpStatus.NOT_FOUND.value());
//        err.setError(e.getErrorCode());
//        err.setMessage(e.getMessage());
//        err.setPath(request.getRequestURI());
//        
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
//	}
//	
	@ExceptionHandler(RequestException.class)
	public ResponseEntity<ListError> handleRequestException(RequestException e, HttpServletRequest request) {
		ListError err = new ListError();
		err.setTimestamp(Instant.now());
		err.setStatus(e.getStatus().value());// Pega o status (400 ou 404) direto da exceção que estourou!
		err.setError(e.getErrorCode());
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		// Retorna o status correto na resposta HTTP
		return ResponseEntity.status(e.getStatus()).body(err);
	}

	// 2. A REDE DE VALIDAÇÃO DOS DTOS (O Spring dispara isso aqui sozinho), É a
	// exceção (MethodArgumentNotValidException) capturada.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ListError> handleValidationException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		ListError error = new ListError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value()); // Pode ser 400 ou 422
		error.setError("ERRO_DE_VALIDACAO");
		error.setMessage("Verifique os campos obrigatórios. Algum dado foi enviado em branco ou com formato inválido.");
		error.setPath(request.getRequestURI());

		// Extrai a mensagem de erro específica, .getBindingResult(): Pense nisso como
		// um "relatório de validação" que o Spring gerou. Ele contém tudo o que deu
		// errado na requisição.
		// getFieldErrors(): O Spring pode ter encontrado vários
		// erros ao mesmo tempo (ex: data errada E nome da empresa em branco). Este
		// método retorna uma lista com todos esses erros.
		// get(0): Como ogetFieldErrors() retorna uma lista, usamos o índice 0 para
		// pegar o primeiro
		// erro da lista.
		// .getDefaultMessage(): Este método pega exatamente
		// a mensagem definida na anotação (ex: @PastOrPresent(message = "A
		// data não pode ser futura")). Ele lê o texto escrito.
		String menssenger = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

		// Prepara o objeto de erro para ser enviado na resposta (JSON)
		error.setMessage(menssenger); // Agora o Postman vai mostrar o erro exato!

		// Registra o local exato onde o erro ocorreu, request: É o objeto que
		// representa a requisição HTTP original feita pelo usuário, getRequestURI():
		// Este método retorna o caminho que o usuário tentou acessar (ex: /budgets ou
		// /employees)
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	// 3. A REDE DE SEGURANÇA (Captura qualquer erro desconhecido - Erro 500)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ListError> handleGenericException(Exception e, HttpServletRequest request) {
		ListError err = new ListError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setError("ERRO_INTERNO_SERVIDOR");
		err.setMessage("Ocorreu um erro inesperado no sistema. Contate o suporte técnico.");
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
}
