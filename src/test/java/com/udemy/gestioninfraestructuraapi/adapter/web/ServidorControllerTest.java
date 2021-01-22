package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorPorCodigoUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase.CrearServidor;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServidorControllerTest {

	@InjectMocks
	private ServidorController servidorController;
	@Mock
	private BuscarServidorPorCodigoUseCase buscarServidorPorCodigoUseCase;
	@Mock
	private BuscarTodosServidorUseCase buscarTodosServidorUseCase;
	@Mock
	private CrearServidorUseCase crearServidorUseCase;
	@Mock
	private BindingResult bindingResult;

	private static final long CODIGO_LONG = 1;
	private static final String CODIGO_STRING = "1";
	private static final String NOMBRE = "splunk";
	private static final String IP = "192.168.1.1";
	private static final String OS = "Windows NT";

	private static final List<String> CAMPOSERROR = new ArrayList<>();
	private static final String MENSAJESCAMPOSERROR = "no debe estar vacio";

	private static final Servidor SERVIDOR = new Servidor();
	private static final CrearServidor CREAR_SERVIDOR = new CrearServidor();
	private List<ObjectError> errors;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		errors = new ArrayList<>();
		CAMPOSERROR.add("nombre");
		CAMPOSERROR.add("id");
		for (String s : CAMPOSERROR) {
			errors.add(new FieldError("BuscadorServidorNombre", s, MENSAJESCAMPOSERROR));
		}

		SERVIDOR.setCodigo(CODIGO_LONG);
		SERVIDOR.setNombre(NOMBRE);
		SERVIDOR.setIp(IP);
		SERVIDOR.setOs(OS);
	}

	@Test
	void testBuscarTodos() throws ApplicationException, ControllerException {
		Mockito.when(buscarTodosServidorUseCase.buscarTodos()).thenReturn(Collections.singletonList(SERVIDOR));
		final ResponseEntity<List<Servidor>> respuesta = servidorController.buscarTodos();
		final List<Servidor> respuestaBody = respuesta.getBody();

		assertNotNull(respuesta);
		assertNotNull(respuestaBody);
		assertEquals(1, respuestaBody.size());
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		
		try {
			Mockito.when(buscarTodosServidorUseCase.buscarTodos()).thenThrow(ApplicationException.class);
			servidorController.buscarTodos();
		} catch (ControllerException e) {
			assertNotNull(e);
			assertNotNull(e.getCause());
		}
	}

	@Test
	void testBuscarPorCodigo() throws ApplicationException, ControllerException {
		Mockito.when(buscarServidorPorCodigoUseCase.buscarServidorPorCodigo(CODIGO_STRING))
				.thenReturn(SERVIDOR);
		ResponseEntity<Servidor> respuesta = servidorController.buscarPorCodigo(CODIGO_STRING);

		assertNotNull(respuesta);
		assertNotNull(respuesta.getBody());
		assertEquals(SERVIDOR, respuesta.getBody());
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		
		try {
			Mockito.when(buscarServidorPorCodigoUseCase.buscarServidorPorCodigo(CODIGO_STRING))
					.thenThrow(ApplicationException.class);
			servidorController.buscarPorCodigo(CODIGO_STRING);
		} catch (ControllerException e) {
			assertNotNull(e);
			assertNotNull(e.getCause());
		}
	}

	@Test
	void crear() throws ApplicationException, ControllerException {
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		Mockito.when(crearServidorUseCase.crear(CREAR_SERVIDOR)).thenReturn(true);
		ResponseEntity<Boolean> servidorRespuesta = servidorController.crear(CREAR_SERVIDOR, bindingResult);
		assertNotNull(servidorRespuesta);
		assertNotNull(servidorRespuesta.getBody());
		assertTrue(servidorRespuesta.getBody());
		assertEquals(HttpStatus.CREATED, servidorRespuesta.getStatusCode());

		try {
			Mockito.when(bindingResult.hasErrors()).thenReturn(true);
			Mockito.when(bindingResult.getAllErrors()).thenReturn(errors);
			servidorController.crear(CREAR_SERVIDOR, bindingResult);
		} catch (ValidationException e) {
			assertNotNull(e);
			assertNull(e.getCause());
			StringBuilder stringBuilder = new StringBuilder();
			for (String s : CAMPOSERROR) {
				stringBuilder.append(s).append(": ").append(MENSAJESCAMPOSERROR).append(" (null)|");
			}
			assertEquals(stringBuilder.toString(), e.getMessage());
		}
		
		try {
			Mockito.when(bindingResult.hasErrors()).thenReturn(false);
			Mockito.when(crearServidorUseCase.crear(CREAR_SERVIDOR)).thenThrow(ApplicationException.class);
			servidorController.crear(CREAR_SERVIDOR, bindingResult);
		} catch (ControllerException e) {
			assertNotNull(e);
			assertNotNull(e.getCause());
		}
	}

	@Test
	void crearFailed() throws ApplicationException, ControllerException {
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		Mockito.when(crearServidorUseCase.crear(CREAR_SERVIDOR)).thenReturn(false);
		ResponseEntity<Boolean> servidorRespuesta = servidorController.crear(CREAR_SERVIDOR, bindingResult);
		assertNotNull(servidorRespuesta);
		assertNotNull(servidorRespuesta.getBody());
		assertFalse(servidorRespuesta.getBody());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, servidorRespuesta.getStatusCode());
	}
}
