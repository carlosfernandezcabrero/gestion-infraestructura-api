package com.udemy.gestioninfraestructuraapi.exception;

public class PersistenceCustomException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersistenceCustomException(String msg, Throwable causa) {
		super(msg, causa);
	}
}
