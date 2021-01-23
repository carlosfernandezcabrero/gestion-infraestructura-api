package com.udemy.gestioninfraestructuraapi.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("No se encontraron datos que devolver para su peticion");
    }
}
