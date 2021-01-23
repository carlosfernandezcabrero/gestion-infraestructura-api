package com.udemy.gestioninfraestructuraapi.exception;

@SuppressWarnings("serial")
public class ValidationException extends RuntimeException {

	public ValidationException(String mensaje){
        super(mensaje);
    }
}
