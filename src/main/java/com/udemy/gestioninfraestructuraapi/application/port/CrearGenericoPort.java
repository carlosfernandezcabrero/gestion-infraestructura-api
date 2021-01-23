package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

public interface CrearGenericoPort<T> {

	/***
	 * Metodo que crear en objeto de tipo T
	 * @param t - objeto generico
	 * @return boolean - true en el caso de que se cree y false en el caso contrario
	 * @throws PersistenceCustomException - lanza las excepcones de la capa de persistencia
	 */
	boolean crearGenerico(T t) throws PersistenceCustomException;

}
