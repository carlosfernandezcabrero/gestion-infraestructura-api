package com.udemy.gestioninfraestructuraapi.application.in;

import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

import java.util.List;

public interface BuscarGrupoResolutorPorDescripcionUseCase {

    /***
     * Metodo que implementa el caso de uso de buscar grupos resolutores por su descripcion
     * @param descripcion - descripcion en forma de String
     * @return List de GrupoResolutor
     */
    List<GrupoResolutor> buscarGrupoResolutorPorDescripcion(String descripcion);
}
