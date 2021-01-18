package com.udemy.gestioninfraestructuraapi.application.in;

import javax.validation.constraints.NotBlank;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

public interface BuscarGrupoResolutorPorNombreUseCase {

    GrupoResolutor buscarPorNombre(BuscarPorNombre buscarPorNombre) throws ApplicationException;

    public final class BuscarPorNombre {

        @NotBlank
        private String nombre;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }

}
