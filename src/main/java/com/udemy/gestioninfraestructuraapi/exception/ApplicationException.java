package com.udemy.gestioninfraestructuraapi.exception;

@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {
	
	public ApplicationException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
