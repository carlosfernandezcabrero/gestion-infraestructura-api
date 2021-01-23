package com.udemy.gestioninfraestructuraapi.adapter.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.List;

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

    private static final GrupoResolutor GRUPO_RESOLUTOR = new GrupoResolutor();
    private static final String NOMBRE = "Storage";

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
        Mockito.when(buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(NOMBRE)).thenReturn(GRUPO_RESOLUTOR);
        final ResponseEntity<GrupoResolutor> responseEntity = grupoResolutorController.buscarPorNombre(NOMBRE);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(GRUPO_RESOLUTOR, responseEntity.getBody());
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
    void buscarPorNombreNotFoundExcepton() throws ApplicationException {
    	Mockito.when(buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(NOMBRE)).thenReturn(null);
        Assertions.assertThrows(NotFoundException.class, ()->    
            grupoResolutorController.buscarPorNombre(NOMBRE)
        );
    }

}
