package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
import com.udemy.gestioninfraestructuraapi.exception.NotFoundException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import com.udemy.gestioninfraestructuraapi.resource.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionHandlerControllerTest {

    private static final ControllerException CONTROLLER_EXCEPTION_GOOD = new ControllerException("Error en BBDD", new Throwable());
    private static final ValidationException VALIDATION_EXCEPTION = new ValidationException("El campo nombre no debe estar vacio|El id no es correcto|");
    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException();

    private static final ExceptionHandlerController EXCEPTION_HANDLER_CONTROLLER = new ExceptionHandlerController();

    @Test()
    void controllerExceptionInternalServerError() {
        final ResponseEntity<ErrorResponse> respuesta = EXCEPTION_HANDLER_CONTROLLER.controllerException(CONTROLLER_EXCEPTION_GOOD);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatusCode());
    }

    @Test
    void validationException() {
        final ResponseEntity<List<ErrorResponse>> respuesta = EXCEPTION_HANDLER_CONTROLLER.validationException(VALIDATION_EXCEPTION);
        assertNotNull(respuesta);
        assertEquals(2, respuesta.getBody().size());
        assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
    }

    @Test
    void notFoundException(){
        final ResponseEntity<ErrorResponse> responseEntity = EXCEPTION_HANDLER_CONTROLLER.notFoundException(NOT_FOUND_EXCEPTION);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}