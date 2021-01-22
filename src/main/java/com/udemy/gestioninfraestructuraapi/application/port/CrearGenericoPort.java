package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

public interface CrearGenericoPort<T> {

	/***
	 * Metodo que crear en objeto de tipo T
	 * @param t
	 * @return boolean - true en el caso de que se cree y false en el caso contrario
	 * @throws PersistenceCustomException
	 */
	boolean crearGenerico(T t) throws PersistenceCustomException;

}
