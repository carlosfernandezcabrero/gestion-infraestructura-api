package com.udemy.gestioninfraestructuraapi.adapter.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorDescripcionUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorNombreUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosGrupoResolutorUseCase;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.NotFoundException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GrupoResolutorControllerTest {

    @InjectMocks
    private GrupoResolutorController grupoResolutorController;

    @Mock
    private BuscarTodosGrupoResolutorUseCase buscarTodosGrupoResolutorUseCase;
    @Mock
    private BuscarGrupoResolutorPorNombreUseCase buscarGrupoResolutorPorNombreUseCase;
    @Mock
    private BuscarGrupoResolutorPorDescripcionUseCase descripcionUseCase;

    private static final GrupoResolutor GRUPO_RESOLUTOR = new GrupoResolutor();
    private static final String NOMBRE = "Storage";
    private static final String DESCRIPCION = "test";

    private static final HttpStatus OK = HttpStatus.OK;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTodos() throws ApplicationException {
        Mockito.when(buscarTodosGrupoResolutorUseCase.buscarTodos()).thenReturn(Collections.singletonList(GRUPO_RESOLUTOR));
        final ResponseEntity<List<GrupoResolutor>> responseEntity = grupoResolutorController.todos();
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void testBuscarPorNombre() throws ApplicationException {
        Mockito.when(buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(NOMBRE)).thenReturn(Collections.singletonList(GRUPO_RESOLUTOR));
        final ResponseEntity<List<GrupoResolutor>> responseEntity = grupoResolutorController.buscarPorNombre(NOMBRE);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    void buscarTodosNotFoundException() throws ApplicationException {
    	Mockito.when(buscarTodosGrupoResolutorUseCase.buscarTodos()).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NotFoundException.class, ()->
        	grupoResolutorController.todos()
        );
    }

    @Test
    void buscarPorNombreNotFoundException() throws ApplicationException {
    	Mockito.when(buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(NOMBRE)).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NotFoundException.class, ()->    
            grupoResolutorController.buscarPorNombre(NOMBRE)
        );
    }

    @Test
    void buscarPorDescripcion(){
        Mockito.when(descripcionUseCase.buscarGrupoResolutorPorDescripcion(DESCRIPCION)).thenReturn(Collections.singletonList(GRUPO_RESOLUTOR));
        final ResponseEntity<List<GrupoResolutor>> responseEntity = grupoResolutorController.buscarPorDescripcion(DESCRIPCION);
        assertNotNull(responseEntity);
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void buscarPorDescripcionNotFoundException(){
        Mockito.when(descripcionUseCase.buscarGrupoResolutorPorDescripcion(DESCRIPCION)).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NotFoundException.class, ()->
                grupoResolutorController.buscarPorDescripcion(DESCRIPCION)
        );
    }

}
