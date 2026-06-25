package com.controle.exception;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListError {
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
