package com.udemy.gestioninfraestructuraapi.adapter.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorNombreUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosGrupoResolutorUseCase;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

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

    private static final List<GrupoResolutor> GRUPO_RESOLUTORS = new ArrayList<>();
    private static final HttpStatus OK = HttpStatus.OK;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTodos() throws ApplicationException, ControllerException {
        Mockito.when(buscarTodosGrupoResolutorUseCase.buscarTodos()).thenReturn(GRUPO_RESOLUTORS);
        final ResponseEntity<List<GrupoResolutor>> responseEntity = grupoResolutorController.todos();
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(GRUPO_RESOLUTORS, responseEntity.getBody());
        assertEquals(OK, responseEntity.getStatusCode());

        try {
            Mockito.when(buscarTodosGrupoResolutorUseCase.buscarTodos()).thenThrow(ApplicationException.class);
            grupoResolutorController.todos();
        } catch (ControllerException e) {
            assertNotNull(e);
            assertNotNull(e.getCause());
        }
    }

    @Test
    void testBuscarPorNombre() throws ApplicationException, ControllerException {
        Mockito.when(buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(NOMBRE)).thenReturn(GRUPO_RESOLUTOR);
        final ResponseEntity<GrupoResolutor> responseEntity = grupoResolutorController.buscarPorNombre(NOMBRE);
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(GRUPO_RESOLUTOR, responseEntity.getBody());
        assertEquals(OK, responseEntity.getStatusCode());

        try {
            Mockito.when(buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(NOMBRE))
                    .thenThrow(ApplicationException.class);
            grupoResolutorController.buscarPorNombre(NOMBRE);
        } catch (ControllerException e) {
            assertNotNull(e);
            assertNotNull(e.getCause());
        }
    }

}
