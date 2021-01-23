package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

import java.util.List;

public interface BuscarTodosGenericoPort <T> {

    /***
     * Metodo que busca todos los elementos de tipo T
     * @return List de T
     * @throws PersistenceCustomException - lanza las excepcones de la capa de persistencia
     */
    List<T> buscarTodos() throws PersistenceCustomException;
}
