package com.udemy.gestioninfraestructuraapi.connection;

import java.sql.Connection;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

public interface TransManager {
	
	Connection connect() throws PersistenceCustomException;
	void closeFinally() throws PersistenceCustomException;

}
