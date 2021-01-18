package com.udemy.gestioninfraestructuraapi.application.in;

import com.udemy.gestioninfraestructuraapi.application.innermodel.CrearServidor;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import org.springframework.stereotype.Component;

@Component
public interface CrearServidorUseCase {

    Servidor crear(CrearServidor crearServidor) throws ApplicationException;
}
