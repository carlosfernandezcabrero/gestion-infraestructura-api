package com.udemy.gestioninfraestructuraapi.application.in;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

public interface BuscarGrupoResolutorPorNombreUseCase {

    /***
     * Metodo que busca un Grupo Resolutor por su nombre
     * @param nombre
     * @return GrupoResolutor
     * @throws ApplicationException
     */
    GrupoResolutor buscarPorNombre(String nombre) throws ApplicationException;

}
