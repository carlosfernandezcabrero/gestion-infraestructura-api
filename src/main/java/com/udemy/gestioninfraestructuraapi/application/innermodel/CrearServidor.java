package com.udemy.gestioninfraestructuraapi.application.innermodel;

import javax.validation.constraints.NotBlank;

public class CrearServidor {
    @NotBlank
    String id;
    @NotBlank
    String nombre;
    @NotBlank
    String ip;
    @NotBlank
    String os;
    @NotBlank
    String grupoResolutor;

    public CrearServidor(){}
}
