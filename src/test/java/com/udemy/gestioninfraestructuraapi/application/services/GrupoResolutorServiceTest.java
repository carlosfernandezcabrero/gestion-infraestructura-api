package com.udemy.gestioninfraestructuraapi.application.services;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarGrupoResolutorPort;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarTodosGenericoPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class GrupoResolutorServiceTest {

    @Mock
    private BuscarTodosGenericoPort<GrupoResolutor> buscarTodosGenericoPort;
    @Mock
    private BuscarGrupoResolutorPort buscarGrupoResolutorPort;

    @InjectMocks
    private GrupoResolutorService grupoResolutorService;

    private static final GrupoResolutor GRUPO_RESOLUTOR = new GrupoResolutor();
    private static final String NOMBRE_GR = "Storage";
    private static final String DESCRIPCION_GR = "Encargado de los datos";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        GRUPO_RESOLUTOR.setNombre(NOMBRE_GR);
        GRUPO_RESOLUTOR.setDescripcion(DESCRIPCION_GR);
    }

    @Test
    void buscarTodos() throws PersistenceCustomException, ApplicationException {
        Mockito.when(buscarTodosGenericoPort.buscarTodos()).thenReturn(Collections.singletonList(GRUPO_RESOLUTOR));
        List<GrupoResolutor> respuesta = grupoResolutorService.buscarTodos();
        assertNotNull(respuesta);
        assertEquals(1, respuesta.size());
    }

    @Test
    void buscarTodosApplicationException() throws PersistenceCustomException {
    	Mockito.when(buscarTodosGenericoPort.buscarTodos()).thenThrow(PersistenceCustomException.class);
        Assertions.assertThrows(ApplicationException.class, ()->
            grupoResolutorService.buscarTodos()
        );
    }

    @Test
    void buscarPorNombre() throws PersistenceCustomException, ApplicationException {
        Mockito.when(buscarGrupoResolutorPort.buscarPorNombre(any(GrupoResolutor.class))).thenReturn(Collections.singletonList(GRUPO_RESOLUTOR));
        final List<GrupoResolutor> respuesta = grupoResolutorService.buscarPorNombre(NOMBRE_GR);
        assertNotNull(respuesta);
        assertEquals(1, respuesta.size());
    }

    @Test
    void buscarPorNombreApplicationException() throws PersistenceCustomException {
    	Mockito.when(buscarGrupoResolutorPort.buscarPorNombre(any(GrupoResolutor.class))).thenThrow(PersistenceCustomException.class);
        Assertions.assertThrows(ApplicationException.class, ()->
            grupoResolutorService.buscarPorNombre(NOMBRE_GR)
        );
    }

    @Test
    void buscarGrupoResolutorPorDescripcion() throws PersistenceCustomException {
        Mockito.when(buscarGrupoResolutorPort.buscarPorDescripcion(any(GrupoResolutor.class))).thenReturn(Collections.singletonList(GRUPO_RESOLUTOR));
        final List<GrupoResolutor> grupoResolutorList = grupoResolutorService.buscarGrupoResolutorPorDescripcion(DESCRIPCION_GR);
        assertNotNull(grupoResolutorList);
        assertEquals(1, grupoResolutorList.size());
    }

    @Test
    void buscarGrupoResolutorPorDescripcionApplicationException() throws PersistenceCustomException {
        Mockito.when(buscarGrupoResolutorPort.buscarPorDescripcion(any(GrupoResolutor.class))).thenThrow(PersistenceCustomException.class);
        Assertions.assertThrows(ApplicationException.class, ()->
                grupoResolutorService.buscarGrupoResolutorPorDescripcion(DESCRIPCION_GR)
        );
    }
}