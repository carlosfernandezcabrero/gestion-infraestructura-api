package com.udemy.gestioninfraestructuraapi.resource;

import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validarNumeroEnteroLargoCorrecto() {
        Validator.validarNumeroEnteroLargo("1");
    }

    @Test
    void validarNumeroEnteroLargoIncorrecto() {
        try{
            Validator.validarNumeroEnteroLargo("1a");
        }catch(ValidationException e){
            assertNotNull(e);
            assertNull(e.getCause());
            assertEquals(e.getMessage(), "El campo debe ser numerico");
        }
    }

    @Test
    void validarNumeroEnteroLargoVacio() {
        try{
            Validator.validarNumeroEnteroLargo("");
        }catch(ValidationException e){
            assertNotNull(e);
            assertNull(e.getCause());
            assertEquals(e.getMessage(), "El campo debe ser numerico");
        }
    }
}