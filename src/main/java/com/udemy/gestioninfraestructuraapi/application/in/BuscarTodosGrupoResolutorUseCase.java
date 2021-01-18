package com.udemy.gestioninfraestructuraapi.application.in;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

public interface BuscarTodosGrupoResolutorUseCase {

    List<GrupoResolutor> buscarTodos() throws ApplicationException;

}
