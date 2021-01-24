package com.udemy.gestioninfraestructuraapi.application.in;

import javax.validation.constraints.NotBlank;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;

public interface CrearServidorUseCase {

	/***
	 * Metodo que crea un Servidor para validacion de datos
	 * @param crearServidor - objeto para validar un servidor
	 * @return boolean - true en el caso de que se cree y false en el caso contrario
	 * @throws ApplicationException - lanza excepciones de aplicacion
	 */
    boolean crear(CrearServidor crearServidor) throws ApplicationException;

    final class CrearServidor {

        @NotBlank
        String nombre;
        @NotBlank
        String ip;
        @NotBlank
        String os;
        @NotBlank
        String grupoResolutor;

		public String getNombre() {
			return nombre;
		}
		public String getIp() {
			return ip;
		}
		public String getOs() {
			return os;
		}
		public String getGrupoResolutor() {
			return grupoResolutor;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public void setOs(String os) {
			this.os = os;
		}
		public void setGrupoResolutor(String grupoResolutor) {
			this.grupoResolutor = grupoResolutor;
		}
		
    }
    
    @SuppressWarnings("serial")
	final class NoExistGrupoResolutor extends RuntimeException {
    	
    	public NoExistGrupoResolutor() {
    		super("No existe el grupo resolutor especificado");
    	}
    }
    
}
