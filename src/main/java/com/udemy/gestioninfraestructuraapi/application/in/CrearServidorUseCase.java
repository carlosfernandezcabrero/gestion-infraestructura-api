package com.udemy.gestioninfraestructuraapi.application.in;

import javax.validation.constraints.NotBlank;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface CrearServidorUseCase {

    Servidor crear(CrearServidor crearServidor) throws ApplicationException;

    public final class CrearServidor {
    	
        @NotBlank
        String codigo;
        @NotBlank
        String nombre;
        @NotBlank
        String ip;
        @NotBlank
        String os;
        @NotBlank
        String grupoResolutor;
        
		public String getCodigo() {
			return codigo;
		}
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
		
		public void setCodigo(String codigo) {
			this.codigo = codigo;
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
    
}
