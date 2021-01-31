package com.udemy.gestioninfraestructuraapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servidor {

    private Long codigo;
    private String nombre;
    private String ip;
    private String os;
    private String grupoResolutor;

}
