package com.udemy.gestioninfraestructuraapi.application.port;

import java.sql.Connection;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

public interface TransManager {
	
	Connection connect() throws PersistenceCustomException;
	void close() throws PersistenceCustomException;
	void closeFinally();

}
