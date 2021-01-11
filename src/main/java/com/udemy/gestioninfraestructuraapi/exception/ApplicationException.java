package com.udemy.gestioninfraestructuraapi.exception;

public class ApplicationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApplicationException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
	public ApplicationException(String msg) {
		super(msg);
	}

}
