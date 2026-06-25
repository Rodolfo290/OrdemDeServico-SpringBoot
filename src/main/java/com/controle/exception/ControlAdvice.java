package com.controle.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControlAdvice  {

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
	public ResponseEntity<ListError> handleRequestException(RequestException e, HttpServletRequest request){
		ListError err = new ListError();
		err.setTimestamp(Instant.now());
        err.setStatus(e.getStatus().value());// Pega o status (400 ou 404) direto da exceção que estourou!
        err.setError(e.getErrorCode());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        
     // Retorna o status correto na resposta HTTP
     		return ResponseEntity.status(e.getStatus()).body(err);
	}
	
	// 2. A REDE DE VALIDAÇÃO DOS DTOS (O Spring dispara isso aqui sozinho)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ListError> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
			ListError err = new ListError();
			err.setTimestamp(Instant.now());
			err.setStatus(HttpStatus.BAD_REQUEST.value()); // Pode ser 400 ou 422
			err.setError("ERRO_DE_VALIDACAO");
			err.setMessage("Verifique os campos obrigatórios. Algum dado foi enviado em branco ou com formato inválido.");
			err.setPath(request.getRequestURI());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
		
		// 3. A REDE DE SEGURANÇA (Captura qualquer erro desconhecido - Erro 500)
		@ExceptionHandler(Exception.class)
		public ResponseEntity<ListError> handleGenericException(Exception e, HttpServletRequest request){
			ListError err = new ListError();
			err.setTimestamp(Instant.now());
			err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			err.setError("ERRO_INTERNO_SERVIDOR");
			err.setMessage("Ocorreu um erro inesperado no sistema. Contate o suporte técnico.");
			err.setPath(request.getRequestURI());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
		}
}
