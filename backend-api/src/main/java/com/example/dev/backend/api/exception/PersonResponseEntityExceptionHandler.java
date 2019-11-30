package com.example.dev.backend.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.dev.backend.api.exception.commons.ExceptionMessageBuilder;
import com.example.dev.backend.api.exception.customs.InativePersonException;
import com.example.dev.backend.api.exception.customs.NonExistentPersonException;

@ControllerAdvice
public class PersonResponseEntityExceptionHandler {

	private static final String INATIVE_PERSON_TO_INCLUDE_IN_APPOITMENT = "inative.person.to.include.in.appointment";
	private static final String INEXISTENT_PERSON_TO_INCLUDE_IN_APPOITMENT = "inexistent.person.to.include.in.appointment";

	@ExceptionHandler({ InativePersonException.class })
	public ResponseEntity<Object> handleInativePersonException(InativePersonException ex) {
		return ResponseEntity.badRequest().body(ExceptionMessageBuilder.buildErrorApi(HttpStatus.BAD_REQUEST,
				ExceptionMessageBuilder.getUserMessage(INATIVE_PERSON_TO_INCLUDE_IN_APPOITMENT), ex.toString()));
	}

	@ExceptionHandler({ NonExistentPersonException.class })
	public ResponseEntity<Object> handleNonExistentPersonException(NonExistentPersonException ex) {
		return ResponseEntity.badRequest().body(ExceptionMessageBuilder.buildErrorApi(HttpStatus.BAD_REQUEST,
				ExceptionMessageBuilder.getUserMessage(INEXISTENT_PERSON_TO_INCLUDE_IN_APPOITMENT), ex.toString()));
	}
}
