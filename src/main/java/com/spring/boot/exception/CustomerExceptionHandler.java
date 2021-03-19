package com.spring.boot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ CustomerNotFoundException.class })
	public ResponseEntity<Object> handleCustomerNotFoundException(Exception exception, WebRequest request) {
		return new ResponseEntity<Object>("Error occurred while fetching the Customer:" + exception.getMessage(),
				new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ NumberFormatException.class })
	public ResponseEntity<Object> handleNumberFormatException(Exception exception, WebRequest request) {
		return new ResponseEntity<Object>(
				"Error occurred while processing the input parameters:" + exception.getMessage(), new HttpHeaders(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ CustomerValidationException.class })
	public ResponseEntity<Object> handleCustomerValidationException(Exception exception, WebRequest request) {
		return new ResponseEntity<Object>(
				"Error occurred while creating/updating the Customer:" + exception.getMessage(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST);
	}

}
