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

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

class ServidorControllerTest {
	
	@Mock
	private BuscarServidorUseCase buscarServidorUseCase;
	
	@InjectMocks
	ServidorController servidorController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testBuscarTodos() {
		final Servidor servidor = new Servidor();
		final List<Servidor> servidores = new ArrayList<>();
		servidores.add(servidor);
		Mockito.when(buscarServidorUseCase.buscarTodos()).thenReturn(servidores);
		final ResponseEntity<List<Servidor>> respuesta = servidorController.buscarTodos();
		final List<Servidor> respuestaBody = respuesta.getBody();
		
		assertNotNull(respuesta);
		assertNotNull(respuestaBody);
		assertFalse(respuestaBody.isEmpty());
		assertEquals(1, respuestaBody.size());
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
	}

	@Test
	void testBuscarPorNombre() {
		fail("Not yet implemented");
	}

	@Test
	void testBuscarPorIp() {
		fail("Not yet implemented");
	}

	@Test
	void testBuscarPorId() {
		fail("Not yet implemented");
	}

	@Test
	void testBuscarPorOs() {
		fail("Not yet implemented");
	}

}
