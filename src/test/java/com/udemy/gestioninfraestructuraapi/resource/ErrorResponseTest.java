package com.udemy.gestioninfraestructuraapi.resource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    private static final ErrorResponse ERROR_RESPONSE = new ErrorResponse("test");

    @Test
    void getMensaje() {
        assertEquals("test", ERROR_RESPONSE.getMensaje());
    }
}