package com.udemy.gestioninfraestructuraapi.exception;

public class ControllerException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControllerException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
