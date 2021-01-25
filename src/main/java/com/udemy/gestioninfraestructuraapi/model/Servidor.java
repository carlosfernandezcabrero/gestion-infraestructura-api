package com.udemy.gestioninfraestructuraapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servidor {

    private Long codigo;
    private String nombre;
    private String ip;
    private String os;
    private String grupoResolutor;

}
