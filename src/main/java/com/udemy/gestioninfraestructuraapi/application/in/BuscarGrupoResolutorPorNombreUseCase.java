package com.udemy.gestioninfraestructuraapi.application.in;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

import java.util.List;

public interface BuscarGrupoResolutorPorNombreUseCase {

    /***
     * Metodo que busca un Grupo Resolutor por su nombre
     * @param nombre - cadena que debe contener el nombre del grupo resolutor
     * @return List de GrupoResolutor
     * @throws ApplicationException - lanza excepciones de la aplicacion
     */
    List<GrupoResolutor> buscarPorNombre(String nombre) throws ApplicationException;

}
