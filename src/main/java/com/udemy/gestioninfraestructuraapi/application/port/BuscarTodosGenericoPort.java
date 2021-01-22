package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

import java.util.List;

public interface BuscarTodosGenericoPort <T> {

    /***
     * Metodo que busca todos los elementos de tipo T
     * @return List de T
     * @throws PersistenceCustomException
     */
    List<T> buscarTodos() throws PersistenceCustomException;
}
