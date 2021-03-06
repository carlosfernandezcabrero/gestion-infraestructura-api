package com.udemy.gestioninfraestructuraapi.application.services;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase.CrearServidor;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarGrupoResolutorPort;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarTodosGenericoPort;
import com.udemy.gestioninfraestructuraapi.application.port.CrearGenericoPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

import org.junit.jupiter.api.Assertions;
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
    @Mock
    private BuscarGrupoResolutorPort buscarGrupoResolutorPort;

    private static final String CODIGO_STRING_GOOD = "1";
    private static final String CODIGO_STRING_BAD = "1sss";
    private static final long CODIGO_LONG_GOOD = 1;
    private static final String IP = "192.168.1.1";
    private static final String NOMBRE = "splunk";
    private static final String OS = "Windows NT";
    private static final String GRUPORESOLUTOR = "Storage";

    private static final Servidor SERVIDOR = new Servidor();
    private static final CrearServidor CREARSERVIDOR = new CrearServidor();

    private static final String MENSAJE_EXCEPTION = "test";
    private static final PersistenceCustomException PERSISTENCE_CUSTOM_EXCEPTION_NULL = new PersistenceCustomException(MENSAJE_EXCEPTION, null);

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
    	Mockito.when(buscarServidorPort.buscarServidorPorCodigo(any(Servidor.class))).thenThrow(PersistenceCustomException.class);
        Assertions.assertThrows(ApplicationException.class, ()->
            service.buscarServidorPorCodigo(CODIGO_STRING_GOOD)
        );
    }

    @Test
    void testBuscarServidorPorCodigoNumberFormatException() throws ApplicationException {
    	Assertions.assertThrows(ValidationException.class, ()->
    		service.buscarServidorPorCodigo(CODIGO_STRING_BAD)
    	);
    }

    @Test
    void testBuscarTodos() throws ApplicationException, PersistenceCustomException {
        Mockito.when(buscarTodosGenericoPort.buscarTodos()).thenReturn(Collections.singletonList(SERVIDOR));
        final List<Servidor> respuesta = service.buscarTodos();
        assertNotNull(respuesta);
        assertEquals(1, respuesta.size());
    }

    @Test
    void testBuscarTodosApplicationException() throws PersistenceCustomException {
    	Mockito.when(buscarTodosGenericoPort.buscarTodos()).thenThrow(PersistenceCustomException.class);
        Assertions.assertThrows(ApplicationException.class, ()->
            service.buscarTodos()
        );
    }
    
    @Test
    void testCrear() throws PersistenceCustomException, ApplicationException {
    	Mockito.when(crearGenericoPort.crearGenerico(any(Servidor.class))).thenReturn(true);
    	Mockito.when(buscarGrupoResolutorPort.buscarPorId(GRUPORESOLUTOR)).thenReturn(new GrupoResolutor());
    	boolean respuesta = service.crear(CREARSERVIDOR);
    	assertTrue(respuesta);
    }

    @Test
    void testCrearApplicationException() throws PersistenceCustomException {
    	Mockito.when(crearGenericoPort.crearGenerico(any(Servidor.class))).thenThrow(PERSISTENCE_CUSTOM_EXCEPTION_NULL);
    	Mockito.when(buscarGrupoResolutorPort.buscarPorId(GRUPORESOLUTOR)).thenReturn(new GrupoResolutor());
        Assertions.assertThrows(ApplicationException.class, ()->
            service.crear(CREARSERVIDOR)
        );
        
        Mockito.when(buscarGrupoResolutorPort.buscarPorId(GRUPORESOLUTOR)).thenReturn(null);
        Assertions.assertThrows(ApplicationException.class, ()->
        	service.crear(CREARSERVIDOR)
        );
    }
}
