package com.example.dev.backend.api.exception.customs;

public class InativePersonException extends RuntimeException {

	private static final long serialVersionUID = 8142050089797787151L;
	
	public InativePersonException() {
	}
	
	public InativePersonException(String msg) {
		super(msg);
	}

	public InativePersonException(String message, Throwable cause) {
		super(message, cause);
	}
}
