package com.udemy.gestioninfraestructuraapi.application.port;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface BuscarServidorPort {
	
	Servidor buscarServidorPorId(Servidor servidor) throws PersistenceCustomException;
    List<Servidor> buscarTodos() throws PersistenceCustomException;
}
