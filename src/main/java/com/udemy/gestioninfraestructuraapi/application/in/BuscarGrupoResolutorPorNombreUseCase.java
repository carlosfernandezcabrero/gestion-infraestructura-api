package com.udemy.gestioninfraestructuraapi.application.in;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

public interface BuscarGrupoResolutorPorNombreUseCase {

    GrupoResolutor buscarPorNombre(String nombre) throws ApplicationException;

}
