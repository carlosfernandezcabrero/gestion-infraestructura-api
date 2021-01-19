package com.udemy.gestioninfraestructuraapi.application.in;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface BuscarTodosServidorUseCase {

    List<Servidor> buscarTodos() throws ApplicationException;

}
