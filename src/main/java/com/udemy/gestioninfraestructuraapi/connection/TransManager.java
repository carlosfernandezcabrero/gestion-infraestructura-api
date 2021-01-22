package com.udemy.gestioninfraestructuraapi.connection;

import java.sql.Connection;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

public interface TransManager {

	/***
	 * Metodo que crea una conexion con la BBDD implementada
	 * @return Connection
	 * @throws PersistenceCustomException
	 */
	Connection connect() throws PersistenceCustomException;

}
