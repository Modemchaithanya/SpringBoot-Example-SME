package com.spring.boot.exception;

public class ProductValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public ProductValidationException() {

	}

	public ProductValidationException(String message) {
		super(message);

	}
}
