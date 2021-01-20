package com.udemy.gestioninfraestructuraapi.resource;

import com.udemy.gestioninfraestructuraapi.exception.ValidationException;

public class Validator {

    private Validator(){}

    public static void validarNumeroEnteroLargo(String cadena){
        try{
			Long.parseLong(cadena);
		}catch(NumberFormatException e){
			throw new ValidationException("El campo debe ser numerico");
		}
    }
    
}
