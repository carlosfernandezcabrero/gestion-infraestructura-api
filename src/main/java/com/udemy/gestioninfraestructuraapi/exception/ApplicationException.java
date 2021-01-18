package com.udemy.gestioninfraestructuraapi.exception;

public class ApplicationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApplicationException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
