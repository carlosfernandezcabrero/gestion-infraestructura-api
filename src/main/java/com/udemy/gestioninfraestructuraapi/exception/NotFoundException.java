package com.udemy.gestioninfraestructuraapi.exception;

public class NotFoundException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NotFoundException(){
        super("No se encontraron datos que devolver para su peticion");
    }
}
