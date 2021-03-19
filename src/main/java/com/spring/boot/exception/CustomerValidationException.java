package com.spring.boot.exception;

public class CustomerValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerValidationException() {

	}

	public CustomerValidationException(String message) {
		super(message);

	}
}
