package com.udemy.gestioninfraestructuraapi.application.in;

import javax.validation.constraints.NotBlank;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;

import lombok.Getter;
import lombok.Setter;

public interface CrearServidorUseCase {

	/***
	 * Metodo que crea un Servidor para validacion de datos
	 * @param crearServidor - objeto para validar un servidor
	 * @return boolean - true en el caso de que se cree y false en el caso contrario
	 * @throws ApplicationException - lanza excepciones de aplicacion
	 */
    boolean crear(CrearServidor crearServidor) throws ApplicationException;

    @Getter
    @Setter
    final class CrearServidor {

        @NotBlank
        String nombre;
        @NotBlank
        String ip;
        @NotBlank
        String os;
        @NotBlank
        String grupoResolutor;
		
    }
    
    @SuppressWarnings("serial")
	final class NoExistGrupoResolutor extends RuntimeException {
    	
    	/***
    	 * Constructor vacio
    	 */
    	public NoExistGrupoResolutor() {
    		super("No existe el grupo resolutor especificado");
    	}
    }
    
}
