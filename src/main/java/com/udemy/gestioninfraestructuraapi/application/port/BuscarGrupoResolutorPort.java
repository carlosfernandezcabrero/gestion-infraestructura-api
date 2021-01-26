package com.udemy.gestioninfraestructuraapi.application.port;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

import java.util.List;

public interface BuscarGrupoResolutorPort {

    /***
     * Metodo que busca un Grupo Resolutor por su nombre
     * @param grupoResolutor - Objeto que encapsula el nombre del grupo resolutor
     * @return List de GrupoResolutor
     * @throws PersistenceCustomException - lanza las excepcones de la capa de persistencia
     */
    List<GrupoResolutor> buscarPorNombre(GrupoResolutor grupoResolutor) throws PersistenceCustomException;
    
    /***
     * Metodo que busca un Grupo Resolutor por su nombre (ID)
     * @param grupoResolutor - nombre del grupo resolutor
     * @return GrupoResolutor
     * @throws PersistenceCustomException - lanza las excepcones de la capa de persistencia
     */
    GrupoResolutor buscarPorId(String grupoResolutor) throws PersistenceCustomException;

    /***
     * Metodo que trae de la capa de persistencia los grupos resolutores que en su descripcion tengan la cadena
     * seteada en el campo descripcion del parametro
     * @param grupoResolutor - objeto que encapsula la descripcion del grupo resolutor
     * @return List de GrupoResolutor
     * @throws PersistenceCustomException - lanza las excepcones de la capa de persistencia
     */
    List<GrupoResolutor> buscarPorDescripcion(GrupoResolutor grupoResolutor) throws PersistenceCustomException;
}
