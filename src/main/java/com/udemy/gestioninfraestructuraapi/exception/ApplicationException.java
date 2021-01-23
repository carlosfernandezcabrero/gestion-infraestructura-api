package com.udemy.gestioninfraestructuraapi.exception;

public class ApplicationException extends RuntimeException {
	
	public ApplicationException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
