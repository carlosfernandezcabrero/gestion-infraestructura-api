package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.NotFoundException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerControllerTest {

    private static final String MENSAJE_EXCEPCION = "test";
    private static final ApplicationException APPLICATION_EXCEPTION_CAUSE = new ApplicationException(MENSAJE_EXCEPCION, new Throwable());
    private static final ApplicationException APPLICATION_EXCEPTION_NOT_CAUSE = new ApplicationException(MENSAJE_EXCEPCION, null);
    private static final ValidationException VALIDATION_EXCEPTION = new ValidationException("El campo nombre no debe estar vacio|El id no es correcto|");
    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException();

    private static final ExceptionHandlerController EXCEPTION_HANDLER_CONTROLLER = new ExceptionHandlerController();

    @Test()
    void controllerExceptionInternalServerError() {
        final ResponseEntity<String> respuesta = EXCEPTION_HANDLER_CONTROLLER.applicationException(APPLICATION_EXCEPTION_CAUSE);
        assertNotNull(respuesta);
        assertEquals(MENSAJE_EXCEPCION, respuesta.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatusCode());
    }

    @Test()
    void controllerExceptionBadRequest(){
        final ResponseEntity<String> respuesta = EXCEPTION_HANDLER_CONTROLLER.applicationException(APPLICATION_EXCEPTION_NOT_CAUSE);
        assertNotNull(respuesta);
        assertEquals(MENSAJE_EXCEPCION, respuesta.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
    }

    @Test
    void validationException() {
        final ResponseEntity<List<String>> respuesta = EXCEPTION_HANDLER_CONTROLLER.validationException(VALIDATION_EXCEPTION);
        assertEquals(2, Objects.requireNonNull(respuesta.getBody()).size());
        assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
    }

    @Test
    void notFoundException(){
        final ResponseEntity<String> responseEntity = EXCEPTION_HANDLER_CONTROLLER.notFoundException(NOT_FOUND_EXCEPTION);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}