package com.udemy.gestioninfraestructuraapi.adapter.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorNombre;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorIp;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorOs;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;

class ServidorControllerTest {
	
	@Mock
	private BuscarServidorUseCase buscarServidorUseCase;
	@Mock
	private BindingResult bindingResult;
	
	@InjectMocks
	private ServidorController servidorController;
	
	final Servidor servidor = new Servidor();
	List<Servidor> servidores;
	final String nombreCampo = "nombre";
	final String mensaje = "no debe estar vacio";
	final String nombreObjeto = "BuscadorServidorNombre";
	final ObjectError fieldError = new FieldError(nombreObjeto, nombreCampo, mensaje);
	List<ObjectError> errors;

	@BeforeEach
	void setUp() throws Exception {
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
	void testBuscarPorNombre() {
		final BuscadorServidorNombre nombre = new BuscadorServidorNombre();
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		Mockito.when(buscarServidorUseCase.buscarServidorPorNombre(nombre)).thenReturn(servidores);
		ResponseEntity<List<Servidor>> respuesta = servidorController.buscarPorNombre(nombre, bindingResult);
		List<Servidor> respuestaBody = respuesta.getBody();
		
		assertNotNull(respuesta);
		assertNotNull(respuestaBody);
		assertFalse(respuestaBody.isEmpty());
		assertEquals(1, respuestaBody.size());
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		
		Mockito.when(buscarServidorUseCase.buscarServidorPorNombre(nombre)).thenThrow(ApplicationException.class);
		try {
			servidorController.buscarPorNombre(nombre, bindingResult);
		}catch(ControllerException e) {
			assertTrue(true);
		}
		
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		Mockito.when(bindingResult.getAllErrors()).thenReturn(errors);
		try{
			servidorController.buscarPorNombre(nombre, bindingResult);
		}catch(ValidationException e) {
			assertEquals(nombreCampo + ": " + mensaje + " (null)", e.getMessage());
		}
	}

	@Test
	void testBuscarPorIp() {
		final BuscadorServidorIp ip = new BuscadorServidorIp();
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		Mockito.when(buscarServidorUseCase.buscarServidorPorIp(ip)).thenReturn(servidores);
		ResponseEntity<List<Servidor>> respuesta = servidorController.buscarPorIp(ip, bindingResult);
		List<Servidor> respuestaBody = respuesta.getBody();
		
		assertNotNull(respuesta);
		assertNotNull(respuestaBody);
		assertFalse(respuestaBody.isEmpty());
		assertEquals(1, respuestaBody.size());
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		
		Mockito.when(buscarServidorUseCase.buscarServidorPorIp(ip)).thenThrow(ApplicationException.class);
		try {
			servidorController.buscarPorIp(ip, bindingResult);
		}catch(ControllerException e) {
			assertTrue(true);
		}
		
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		Mockito.when(bindingResult.getAllErrors()).thenReturn(errors);
		try{
			respuesta = servidorController.buscarPorIp(ip, bindingResult);
		}catch(ValidationException e) {
			assertEquals(nombreCampo + ": " + mensaje + " (null)", e.getMessage());
		}
	}

	@Test
	void testBuscarPorId() {
		String id = "test";
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		Mockito.when(buscarServidorUseCase.buscarServidorPorId(id)).thenReturn(servidor);
		ResponseEntity<Servidor> respuesta = servidorController.buscarPorId(id);
		
		assertNotNull(respuesta);
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		
		Mockito.when(buscarServidorUseCase.buscarServidorPorId(id)).thenThrow(ApplicationException.class);
		try {
			servidorController.buscarPorId(id);
		}catch(ControllerException e) {
			assertEquals("null", e.getMessage());
		}
	}

	@Test
	void testBuscarPorOs() {
		final BuscadorServidorOs os = new BuscadorServidorOs();
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		Mockito.when(buscarServidorUseCase.buscarServidorPorOs(os)).thenReturn(servidores);
		ResponseEntity<List<Servidor>> respuesta = servidorController.buscarPorOs(os, bindingResult);
		List<Servidor> respuestaBody = respuesta.getBody();
		
		assertNotNull(respuesta);
		assertNotNull(respuestaBody);
		assertFalse(respuestaBody.isEmpty());
		assertEquals(1, respuestaBody.size());
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		
		Mockito.when(buscarServidorUseCase.buscarServidorPorOs(os)).thenThrow(ApplicationException.class);
		try {
			servidorController.buscarPorOs(os, bindingResult);
		}catch(ControllerException e) {
			assertTrue(true);
		}
		
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		Mockito.when(bindingResult.getAllErrors()).thenReturn(errors);
		try{
			respuesta = servidorController.buscarPorOs(os, bindingResult);
		}catch(ValidationException e) {
			assertEquals(nombreCampo + ": " + mensaje + " (null)", e.getMessage());
		}
	}

}
