package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface BuscarServidorPort {

	/***
	 * Metodo que busca un Servidor por su codigo
	 * @param servidor - objeto que encapsula el codigo del servidor
	 * @return Servidor
	 * @throws PersistenceCustomException - lanza las excepcones de la capa de persistencia
	 */
	Servidor buscarServidorPorCodigo(Servidor servidor) throws PersistenceCustomException;
}
