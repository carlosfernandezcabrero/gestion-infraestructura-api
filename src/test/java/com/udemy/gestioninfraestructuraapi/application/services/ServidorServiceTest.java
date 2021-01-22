package com.udemy.gestioninfraestructuraapi.application.services;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase.CrearServidor;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarTodosGenericoPort;
import com.udemy.gestioninfraestructuraapi.application.port.CrearGenericoPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.NotFoundException;
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
    private BuscarTodosGenericoPort<Servidor> buscarTodosGenericoPort;
    @Mock
    private CrearGenericoPort<Servidor> crearGenericoPort;

    private static final String CODIGO_STRING_GOOD = "1";
    private static final String CODIGO_STRING_BAD = "1sss";
    private static final long CODIGO_LONG_GOOD = 1;
    private static final String IP = "192.168.1.1";
    private static final String NOMBRE = "splunk";
    private static final String OS = "Windows NT";
    private static final String GRUPORESOLUTOR = "Storage";

    private static final Servidor SERVIDOR = new Servidor();
    private static final CrearServidor CREARSERVIDOR = new CrearServidor();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        SERVIDOR.setCodigo(CODIGO_LONG_GOOD);
        SERVIDOR.setIp(IP);
        SERVIDOR.setNombre(NOMBRE);
        SERVIDOR.setOs(OS);
        SERVIDOR.setGrupoResolutor(GRUPORESOLUTOR);

        CREARSERVIDOR.setIp(IP);
        CREARSERVIDOR.setNombre(NOMBRE);
        CREARSERVIDOR.setOs(OS);
        CREARSERVIDOR.setGrupoResolutor(GRUPORESOLUTOR);
    }

    @Test
    void testBuscarServidorPorCodigo() throws ApplicationException, PersistenceCustomException {
        Mockito.when(buscarServidorPort.buscarServidorPorCodigo(any(Servidor.class))).thenReturn(SERVIDOR);
        final Servidor respuesta = service.buscarServidorPorCodigo(CODIGO_STRING_GOOD);
        assertNotNull(respuesta);
        assertEquals(SERVIDOR, respuesta);
    }

    @Test
    void testBuscarServidorPorCodigoApplicationException() throws PersistenceCustomException {
        try{
            Mockito.when(buscarServidorPort.buscarServidorPorCodigo(any(Servidor.class))).thenThrow(PersistenceCustomException.class);
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
    void testBuscarServidorPorCodigoNotFoundException() throws PersistenceCustomException, ApplicationException {
        try{
            Mockito.when(buscarServidorPort.buscarServidorPorCodigo(any(Servidor.class))).thenReturn(null);
            service.buscarServidorPorCodigo(CODIGO_STRING_GOOD);
        }catch (NotFoundException e){
            assertNotNull(e);
            assertNull(e.getCause());
        }
    }

    @Test
    void testBuscarTodos() throws ApplicationException, PersistenceCustomException {
        Mockito.when(buscarTodosGenericoPort.buscarTodos()).thenReturn(Collections.singletonList(SERVIDOR));
        final List<Servidor> respuesta = service.buscarTodos();
        assertNotNull(respuesta);
        assertEquals(1, respuesta.size());
    }

    @Test
    void testBuscarTodosApplicationException() throws PersistenceCustomException{
    	try {
    		Mockito.when(buscarTodosGenericoPort.buscarTodos()).thenThrow(PersistenceCustomException.class);
    		service.buscarTodos();
    	}catch(ApplicationException e) {
    		assertNotNull(e);
    		assertNotNull(e.getCause());
    	}
    }

    @Test
    void testBuscarTodosNotFoundException() throws PersistenceCustomException, ApplicationException {
        try{
            Mockito.when(buscarTodosGenericoPort.buscarTodos()).thenReturn(Collections.emptyList());
            service.buscarTodos();
        }catch (NotFoundException e){
            assertNotNull(e);
            assertNull(e.getCause());
        }
    }
    
    @Test
    void testCrear() throws PersistenceCustomException, ApplicationException {
    	Mockito.when(crearGenericoPort.crearGenerico(any(Servidor.class))).thenReturn(true);
    	boolean respuesta = service.crear(CREARSERVIDOR);
    	assertTrue(respuesta);
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
