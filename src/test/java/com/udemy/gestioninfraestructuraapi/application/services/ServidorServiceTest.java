package com.udemy.gestioninfraestructuraapi.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ServidorServiceTest {

    @InjectMocks
    private ServidorService service;

    @Mock
    private BuscarServidorPort buscarServidorPort;

    private static final String CODIGO_STRING_GOOD = "1";
    private static final String CODIGO_STRING_BAD = "1sss";
    private static final long CODIGO_LONG_GOOD = 1;
    private static final String IP = "192.168.1.1";
    private static final String NOMBRE = "splunk";
    private static final String OS = "Windows NT";

    private static final Servidor SERVIDOR = new Servidor();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        SERVIDOR.setCodigo(CODIGO_LONG_GOOD);
        SERVIDOR.setIp(IP);
        SERVIDOR.setNombre(NOMBRE);
        SERVIDOR.setOs(OS);
    }

    @Test
    void testBuscarServidorPorCodigo() throws ApplicationException, PersistenceCustomException {
        Mockito.when(buscarServidorPort.buscarServidorPorId(any(Servidor.class))).thenReturn(SERVIDOR);
        final Servidor respuesta = service.buscarServidorPorCodigo(CODIGO_STRING_GOOD);
        assertNotNull(respuesta);
        assertEquals(SERVIDOR, respuesta);
    }

    @Test
    void testBuscarServidorPorCodigoApplicationException() throws PersistenceCustomException {
        try{
            Mockito.when(buscarServidorPort.buscarServidorPorId(any(Servidor.class))).thenThrow(PersistenceCustomException.class);
            service.buscarServidorPorCodigo(CODIGO_STRING_GOOD);
        }catch(ApplicationException e){
            assertNotNull(e);
            assertNotNull(e.getCause());
        }
    }

    @Test
    void testBuscarServidorPorCodigoNumberFormatException() throws ApplicationException {
        try{
            service.buscarServidorPorCodigo(CODIGO_STRING_BAD);
        }catch(ValidationException e){
            assertNotNull(e);
            assertNull(e.getCause());
            assertEquals("El campo debe ser numerico", e.getMessage());
        }
    }
    
}
