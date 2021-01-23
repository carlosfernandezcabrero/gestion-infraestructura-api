package com.udemy.gestioninfraestructuraapi.exception;

public class ValidationException extends RuntimeException {

	public ValidationException(String mensaje){
        super(mensaje);
    }
}
