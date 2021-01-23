package com.udemy.gestioninfraestructuraapi.resource;

import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

class ValidatorTest {

    @Test
    void validarNumeroEnteroLargoCorrecto() {
        Validator.validarNumeroEnteroLargo("1");
        assertTrue(true);
    }

    @Test
    void validarNumeroEnteroLargoIncorrecto() {
    	Assertions.assertThrows(ValidationException.class, ()->
    		Validator.validarNumeroEnteroLargo("1a")
    	);
    }

    @Test
    void validarNumeroEnteroLargoVacio() {
    	Assertions.assertThrows(ValidationException.class, ()->
            Validator.validarNumeroEnteroLargo("")
        );
    }
}