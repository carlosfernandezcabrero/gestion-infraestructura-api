package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

public interface BuscarGrupoResolutorPort {

    /***
     * Metodo que busca un Grupo Resolutor por su nombre
     * @param grupoResolutor
     * @return GrupoResolutor
     * @throws PersistenceCustomException
     */
    GrupoResolutor buscarPorNombre(GrupoResolutor grupoResolutor) throws PersistenceCustomException;
}
