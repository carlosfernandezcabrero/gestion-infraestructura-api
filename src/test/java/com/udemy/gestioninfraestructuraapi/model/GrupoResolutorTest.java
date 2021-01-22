package com.udemy.gestioninfraestructuraapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrupoResolutorTest {

    private static final GrupoResolutor GRUPO_RESOLUTOR = new GrupoResolutor();

    @Test
    void setNombre() {
        String nombre = "splunk-server";
        GRUPO_RESOLUTOR.setNombre(nombre);
        assertEquals(nombre, GRUPO_RESOLUTOR.getNombre());
    }

    @Test
    void setDescripcion() {
        String descripcion = "test";
        GRUPO_RESOLUTOR.setDescripcion(descripcion);
        assertEquals(descripcion, GRUPO_RESOLUTOR.getDescripcion());
    }
}