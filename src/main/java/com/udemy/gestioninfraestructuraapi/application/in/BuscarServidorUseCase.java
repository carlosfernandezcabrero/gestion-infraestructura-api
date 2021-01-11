package com.udemy.gestioninfraestructuraapi.application.in;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface BuscarServidorUseCase {

    Servidor buscarServidorPorId(String id);
    List<Servidor> buscarServidorPorNombre(BuscadorServidorNombre nombre);
    List<Servidor> buscarServidorPorIp(BuscadorServidorIp ip);
    List<Servidor> buscarServidorPorOs(BuscadorServidorOs os);
    List<Servidor> buscarTodos();
    
    final class BuscadorServidorNombre{
    	
    	@NotBlank
    	private String nombre;

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
    	
    }
    
    final class BuscadorServidorIp{
    	
    	@NotBlank
    	private String ip;
    	
    	public String getIp() {
    		return ip;
    	}
    	
    	public void setIp(String ip) {
    		this.ip = ip;
    	}
    	
    }
    
    final class BuscadorServidorOs{
    	
    	@NotBlank
    	private String os;
    	
    	public String getOs() {
    		return os;
    	}
    	
    	public void setOs(String os) {
    		this.os = os;
    	}
    	
    }

}
