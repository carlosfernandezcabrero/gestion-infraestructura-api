package com.udemy.gestioninfraestructuraapi.exception;

public class ControllerException extends RuntimeException {

	public ControllerException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
