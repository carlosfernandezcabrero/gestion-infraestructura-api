package com.udemy.gestioninfraestructuraapi.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.innermodel.BuscarPorId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

class ServidorServiceTest {

	@Mock
	BuscarServidorPort buscarServidorPort;

	@InjectMocks
	private ServidorService servidorService;

	final Servidor servidor = new Servidor(1, "splunk-server", "192.168.1.10", "Windows NT");
	private List<Servidor> servidores;

	@BeforeEach
	void setUp() {
		servidores = new ArrayList<>();
		servidores.add(new Servidor());

		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testBuscarServidorPorId() throws PersistenceCustomException {
		try {
			final String id = "1";
			final BuscarPorId buscarPorId = new BuscarPorId(id);
			when(buscarServidorPort.buscarServidorPorId(new Servidor())).thenReturn(new Servidor());
			Servidor servidorRespuesta = servidorService.buscarServidorPorId(buscarPorId);

			assertNotNull(servidorRespuesta);
			assertEquals(servidor, servidorRespuesta);

			when(buscarServidorPort.buscarServidorPorId(servidor)).thenThrow(PersistenceCustomException.class);
			servidorService.buscarServidorPorId(buscarPorId);
		} catch (ApplicationException e) {
			assertTrue(true);
		}
	}

	@Test
	void testBuscarTodos() throws PersistenceCustomException {
		try {
			when(buscarServidorPort.buscarTodos()).thenReturn(servidores);
			List<Servidor> respuesta = servidorService.buscarTodos();

			assertNotNull(respuesta);
			assertEquals(servidores, respuesta);
			assertFalse(respuesta.isEmpty());
			assertEquals(1, respuesta.size());

			when(buscarServidorPort.buscarTodos()).thenThrow(PersistenceCustomException.class);
			servidorService.buscarTodos();
		} catch (ApplicationException e) {
			assertTrue(true);
		}
	}
}
