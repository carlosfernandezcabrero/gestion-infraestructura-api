package com.udemy.gestioninfraestructuraapi.exception;

public class ValidationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String mensaje){
        super(mensaje);
    }
}
