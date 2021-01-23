package com.udemy.gestioninfraestructuraapi.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super("No se encontraron datos que devolver para su peticion");
    }
}
