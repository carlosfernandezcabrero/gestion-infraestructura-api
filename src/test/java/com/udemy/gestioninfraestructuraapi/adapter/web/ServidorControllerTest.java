package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.innermodel.BuscarPorId;
import com.udemy.gestioninfraestructuraapi.application.innermodel.CrearServidor;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ServidorControllerTest {

	@Mock
	private BuscarServidorUseCase buscarServidorUseCase;
	@Mock
	private CrearServidorUseCase crearServidorUseCase;
	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private ServidorController servidorController;

	private static final List<String> CAMPOSERROR = new ArrayList<>();
	private static final String MENSAJESCAMPOSERROR = "no debe estar vacio";

	private final Servidor servidor = new Servidor(1, "splunk-server", "192.168.1.10", "Windows NT");
	private List<Servidor> servidores;
	private List<ObjectError> errors;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		servidores = new ArrayList<>();
		errors = new ArrayList<>();
		servidores.add(servidor);

		CAMPOSERROR.add("nombre");
		CAMPOSERROR.add("id");
		for (String s : CAMPOSERROR) {
			errors.add(new FieldError("BuscadorServidorNombre", s, MENSAJESCAMPOSERROR));
		}
	}

	@Test
	void testBuscarTodos() throws ApplicationException {
		try {
			Mockito.when(buscarServidorUseCase.buscarTodos()).thenReturn(servidores);
			final ResponseEntity<List<Servidor>> respuesta = servidorController.buscarTodos();
			final List<Servidor> respuestaBody = respuesta.getBody();

			assertNotNull(respuesta);
			assertNotNull(respuestaBody);
			assertFalse(respuestaBody.isEmpty());
			assertEquals(1, respuestaBody.size());
			assertEquals(HttpStatus.OK, respuesta.getStatusCode());

			Mockito.when(buscarServidorUseCase.buscarTodos()).thenThrow(ApplicationException.class);
			servidorController.buscarTodos();
		} catch (ControllerException e) {
			assertNotNull(e);
			assertNotNull(e);
		}
	}

	@Test
	void testBuscarPorId() throws ApplicationException {
		String id = "1";
		final BuscarPorId buscarPorId = new BuscarPorId(id);

		try {
			try {
				Mockito.when(bindingResult.hasErrors()).thenReturn(false);
				Mockito.when(buscarServidorUseCase.buscarServidorPorId(buscarPorId)).thenReturn(servidor);
				ResponseEntity<Servidor> respuesta = servidorController.buscarPorId(buscarPorId, bindingResult);

				assertNotNull(respuesta);
				assertEquals(HttpStatus.OK, respuesta.getStatusCode());

				Mockito.when(bindingResult.hasErrors()).thenReturn(true);
				Mockito.when(bindingResult.getAllErrors()).thenReturn(errors);
				respuesta = servidorController.buscarPorId(buscarPorId, bindingResult);
				assertNotNull(respuesta);
				assertEquals(HttpStatus.OK, respuesta.getStatusCode());
			} catch (ValidationException e) {
				assertNotNull(e);
				assertNull(e.getCause());
				assertEquals(CAMPOSERROR.get(0) + ": " + MENSAJESCAMPOSERROR + " (null)", e.getMessage());
			}

			Mockito.when(bindingResult.hasErrors()).thenReturn(false);
			Mockito.when(buscarServidorUseCase.buscarServidorPorId(buscarPorId)).thenThrow(ApplicationException.class);
			servidorController.buscarPorId(buscarPorId, bindingResult);
		} catch (ControllerException e) {
			assertNotNull(e);
			assertNotNull(e.getCause());
		}
	}

	@Test
	void crear() throws ApplicationException {
		try {
			Mockito.when(bindingResult.hasErrors()).thenReturn(false);
			Mockito.when(crearServidorUseCase.crear(any(CrearServidor.class))).thenReturn(new Servidor());
			ResponseEntity<Servidor> servidorRespuesta = servidorController.crear(new CrearServidor(), bindingResult);
			assertNotNull(servidorRespuesta.getBody());
			assertEquals(HttpStatus.OK, servidorRespuesta.getStatusCode());

			try {
				Mockito.when(bindingResult.hasErrors()).thenReturn(true);
				Mockito.when(bindingResult.getAllErrors()).thenReturn(errors);
				servidorController.crear(new CrearServidor(), bindingResult);
			} catch (ValidationException e) {
				assertNotNull(e);
				assertNull(e.getCause());
				StringBuilder stringBuilder = new StringBuilder();
				for (String s : CAMPOSERROR) {
					stringBuilder.append(s + ": " + MENSAJESCAMPOSERROR + " (null)|");
				}
				assertEquals(stringBuilder.toString(), e.getMessage());
			}

			Mockito.when(bindingResult.hasErrors()).thenReturn(false);
			Mockito.when(crearServidorUseCase.crear(any(CrearServidor.class))).thenThrow(ApplicationException.class);
			servidorController.crear(new CrearServidor(), bindingResult);
		} catch (ControllerException e) {
			assertNotNull(e);
			assertNotNull(e.getCause());
		}
	}
}
