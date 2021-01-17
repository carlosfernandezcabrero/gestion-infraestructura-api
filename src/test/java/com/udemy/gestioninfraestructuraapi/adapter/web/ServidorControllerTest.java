package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
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

class ServidorControllerTest {
	
	@Mock
	private BuscarServidorUseCase buscarServidorUseCase;
	@Mock
	private BindingResult bindingResult;
	
	@InjectMocks
	private ServidorController servidorController;

	private final String fieldErrorString = "nombre";
	private final String messageFieldError = "no debe estar vacio";
	
	private final Servidor servidor = new Servidor(1, "splunk-server", "192.168.1.10",
			"Windows NT");
	private List<Servidor> servidores;
	private final ObjectError fieldError = new FieldError("BuscadorServidorNombre",
			fieldErrorString, messageFieldError);
	private List<ObjectError> errors;
	private final String validationExceptionMessage = fieldErrorString + ": " + messageFieldError + " (null)";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		servidores = new ArrayList<>();
		errors = new ArrayList<>();
		servidores.add(servidor);
		errors.add(fieldError);
	}

	@Test
	void testBuscarTodos() {
		Mockito.when(buscarServidorUseCase.buscarTodos()).thenReturn(servidores);
		final ResponseEntity<List<Servidor>> respuesta = servidorController.buscarTodos();
		final List<Servidor> respuestaBody = respuesta.getBody();
		
		assertNotNull(respuesta);
		assertNotNull(respuestaBody);
		assertFalse(respuestaBody.isEmpty());
		assertEquals(1, respuestaBody.size());
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		
		Mockito.when(buscarServidorUseCase.buscarTodos()).thenThrow(ApplicationException.class);
		try {
			servidorController.buscarTodos();
		}catch(ControllerException e) {
			assertTrue(true);
		}
	}

	@Test
	void testBuscarPorId() {
		Integer id = 1;
		final BuscarServidorUseCase.BuscarPorId buscarPorId = new BuscarServidorUseCase.BuscarPorId();
		buscarPorId.setId(id);
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		Mockito.when(buscarServidorUseCase.buscarServidorPorId(buscarPorId)).thenReturn(servidor);
		ResponseEntity<Servidor> respuesta = servidorController.buscarPorId(buscarPorId, bindingResult);
		
		assertNotNull(respuesta);
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());

		try {
			Mockito.when(bindingResult.hasErrors()).thenReturn(true);
			Mockito.when(bindingResult.getAllErrors()).thenReturn(errors);
			respuesta = servidorController.buscarPorId(buscarPorId, bindingResult);
			assertNotNull(respuesta);
			assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		}catch(ValidationException e){
			assertEquals(validationExceptionMessage, e.getMessage());
		}

		try {
			Mockito.when(bindingResult.hasErrors()).thenReturn(false);
			Mockito.when(buscarServidorUseCase.buscarServidorPorId(buscarPorId)).thenThrow(ApplicationException.class);
			servidorController.buscarPorId(buscarPorId, bindingResult);
		}catch(ControllerException e) {
			assertTrue(true);
		}
	}
}
