package com.example.dev.backend.api.exception.customs;

public class NonExistentPersonException extends RuntimeException {

	private static final long serialVersionUID = 5462089921481775110L;
	
	public NonExistentPersonException() {
	}
	
	public NonExistentPersonException(String msg) {
		super(msg);
	}

	public NonExistentPersonException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
