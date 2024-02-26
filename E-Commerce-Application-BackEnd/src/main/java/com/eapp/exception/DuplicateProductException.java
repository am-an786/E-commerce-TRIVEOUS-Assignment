package com.eapp.exception;

public class DuplicateProductException extends RuntimeException {
	
	public DuplicateProductException() {
		super();
	}
	public DuplicateProductException(String message) {
		super(message);
	}
}
