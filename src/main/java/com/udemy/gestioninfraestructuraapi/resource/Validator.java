package com.udemy.gestioninfraestructuraapi.resource;

import com.udemy.gestioninfraestructuraapi.exception.ValidationException;

public class Validator {

    private Validator(){}

    /***
     * Metodo que valida si una cadena puede convertir se a un numero entero largo
     * @param cadena - numero entero largo en formato cadena
     */
    public static void validarNumeroEnteroLargo(String cadena){
        try{
			Long.parseLong(cadena);
		}catch(NumberFormatException e){
			throw new ValidationException("El campo debe ser numerico");
		}
    }
    
}
