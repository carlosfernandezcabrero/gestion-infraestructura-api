package com.udemy.gestioninfraestructuraapi.application.in;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

public interface BuscarTodosGrupoResolutorUseCase {

    /***
     * Metodo que busca todos los Grupos Resolutores
     * @return List de GrupoResolutor
     * @throws ApplicationException
     */
    List<GrupoResolutor> buscarTodos() throws ApplicationException;

}
