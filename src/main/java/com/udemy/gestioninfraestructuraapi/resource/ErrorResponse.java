package com.udemy.gestioninfraestructuraapi.resource;

public class ErrorResponse {
	
	private String mensaje;
	
	public ErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }
	
	public String getMensaje() {
		return this.mensaje;
	}

}
