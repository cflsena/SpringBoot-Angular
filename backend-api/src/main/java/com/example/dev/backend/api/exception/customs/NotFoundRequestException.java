package com.example.dev.backend.api.exception.customs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundRequestException extends RuntimeException {

	private static final long serialVersionUID = -8296171132895045641L;
	
	public NotFoundRequestException() {
	}
	
	public NotFoundRequestException(String msg) {
		super(msg);
	}

	public NotFoundRequestException(String message, Throwable cause) {
		super(message, cause);
	}

}
