package com.udemy.gestioninfraestructuraapi.application.in;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface BuscarServidorPorCodigoUseCase {

    Servidor buscarServidorPorCodigo(String codigo) throws ApplicationException;

}
