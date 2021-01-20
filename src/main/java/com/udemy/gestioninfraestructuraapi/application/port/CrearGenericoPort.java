package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

public interface CrearGenericoPort<T> {
	
	T crearGenerico(T t) throws PersistenceCustomException;

}
