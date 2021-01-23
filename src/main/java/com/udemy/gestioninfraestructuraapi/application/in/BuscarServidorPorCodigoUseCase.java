package com.udemy.gestioninfraestructuraapi.application.in;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface BuscarServidorPorCodigoUseCase {

    /***
     * Metodo que busca un Servidor por su codigo
     * @param codigo - identifica al  servidor
     * @return Servidor
     * @throws ApplicationException - lanza excepciones de la capa de aplicacion
     */
    Servidor buscarServidorPorCodigo(String codigo) throws ApplicationException;

}
