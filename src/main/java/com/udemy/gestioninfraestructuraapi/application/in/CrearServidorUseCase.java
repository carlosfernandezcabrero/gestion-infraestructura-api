package com.udemy.gestioninfraestructuraapi.application.in;

import javax.validation.constraints.NotBlank;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

public interface CrearServidorUseCase {

    Servidor crear(CrearServidor crearServidor) throws ApplicationException;

    public final class CrearServidor {
        @NotBlank
        String codigo;
        @NotBlank
        String nombre;
        @NotBlank
        String ip;
        @NotBlank
        String os;
        @NotBlank
        String grupoResolutor;
    }
}
