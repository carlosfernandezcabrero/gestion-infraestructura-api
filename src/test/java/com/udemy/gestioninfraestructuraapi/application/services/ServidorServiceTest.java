package com.udemy.gestioninfraestructuraapi.application.services;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase.CrearServidor;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.application.port.CrearGenericoPort;
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
    @Mock
    private CrearGenericoPort<Servidor> crearGenericoPort;

    private static final String CODIGO_STRING_GOOD = "1";
    private static final String CODIGO_STRING_BAD = "1sss";
    private static final long CODIGO_LONG_GOOD = 1;
    private static final String IP = "192.168.1.1";
    private static final String NOMBRE = "splunk";
    private static final String OS = "Windows NT";

    private static final Servidor SERVIDOR = new Servidor();
    private static final CrearServidor CREARSERVIDOR = new CrearServidor();

    private static final List<Servidor> SERVIDOR_LIST = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        SERVIDOR.setCodigo(CODIGO_LONG_GOOD);
        SERVIDOR.setIp(IP);
        SERVIDOR.setNombre(NOMBRE);
        SERVIDOR.setOs(OS);
        
        CREARSERVIDOR.setCodigo(CODIGO_STRING_GOOD);
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

    @Test
    void testBuscarTodos() throws ApplicationException, PersistenceCustomException {
        Mockito.when(buscarServidorPort.buscarTodos()).thenReturn(SERVIDOR_LIST);
        final List<Servidor> respuesta = service.buscarTodos();
        assertNotNull(respuesta);
        assertEquals(SERVIDOR_LIST, respuesta);
    }

    @Test
    void testBuscarTodosApplicationException() throws PersistenceCustomException{
    	try {
    		Mockito.when(buscarServidorPort.buscarTodos()).thenThrow(PersistenceCustomException.class);
    		service.buscarTodos();
    	}catch(ApplicationException e) {
    		assertNotNull(e);
    		assertNotNull(e.getCause());
    	}
    }
    
    @Test
    void testCrear() throws PersistenceCustomException, ApplicationException {
    	Mockito.when(crearGenericoPort.crearGenerico(any(Servidor.class))).thenReturn(SERVIDOR);
    	Servidor respuesta = service.crear(CREARSERVIDOR);
    	assertNotNull(respuesta);
    	assertEquals(SERVIDOR, respuesta);
    }
    
    @Test
    void testCrearApplicationException() throws PersistenceCustomException {
    	try {
	    	Mockito.when(crearGenericoPort.crearGenerico(any(Servidor.class))).thenThrow(PersistenceCustomException.class);
	    	service.crear(CREARSERVIDOR);
    	}catch(ApplicationException e) {
    		assertNotNull(e);
    		assertNotNull(e.getCause());
    	}
    }
}
