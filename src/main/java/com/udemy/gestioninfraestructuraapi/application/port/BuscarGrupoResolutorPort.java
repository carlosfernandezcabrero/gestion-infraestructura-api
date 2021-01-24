package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

public interface BuscarGrupoResolutorPort {

    /***
     * Metodo que busca un Grupo Resolutor por su nombre
     * @param grupoResolutor - Objeto que encapsula el nombre del grupo resolutor
     * @return GrupoResolutor
     * @throws PersistenceCustomException - lanza las excepcones de la capa de persistencia
     */
    GrupoResolutor buscarPorNombre(GrupoResolutor grupoResolutor) throws PersistenceCustomException;
    
    /***
     * Metodo que busca un Grupo Resolutor por su nombre (ID)
     * @param grupoResolutor - nombre del grupo resolutor
     * @return GrupoResolutor
     * @throws PersistenceCustomException - lanza las excepcones de la capa de persistencia
     */
    GrupoResolutor buscarPorId(String grupoResolutor) throws PersistenceCustomException;
}
