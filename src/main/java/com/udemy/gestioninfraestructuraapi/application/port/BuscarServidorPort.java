package com.udemy.gestioninfraestructuraapi.application.port;

import java.sql.Connection;
import java.util.List;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface BuscarServidorPort {
	
	Servidor buscarServidorPorId(Connection con, String id) throws PersistenceCustomException;
    List<Servidor> buscarServidorPorNombre(Connection con, String nombre) throws PersistenceCustomException;
    List<Servidor> buscarServidorPorIp(Connection con, String ip) throws PersistenceCustomException;
    List<Servidor> buscarServidorPorOs(Connection con, String os) throws PersistenceCustomException;
    List<Servidor> buscarTodos(Connection con) throws PersistenceCustomException;

}
