package com.udemy.gestioninfraestructuraapi.model;

public class GrupoResolutor {

    private String nombre;

    public GrupoResolutor(){}

    public GrupoResolutor(String nombre){
        this.setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
