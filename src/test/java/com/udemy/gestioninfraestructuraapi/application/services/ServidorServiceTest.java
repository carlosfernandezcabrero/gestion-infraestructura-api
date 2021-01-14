package com.udemy.gestioninfraestructuraapi.application.services;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.application.port.TransManager;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorIp;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorNombre;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorOs;

class ServidorServiceTest {
	
	@InjectMocks
	private ServidorService servidorService;
	
	@Mock
	private TransManager transManager;
	@Mock
	private BuscarServidorPort buscarServidorPort;
	
	private Connection con;
	private final Servidor servidor = new Servidor();
	private List<Servidor> servidores;
	private final BuscadorServidorNombre buscadorServidorNombre = new BuscadorServidorNombre();
	private final BuscadorServidorOs buscadorServidorOs = new BuscadorServidorOs();
	private final BuscadorServidorIp buscadorServidorIp = new BuscadorServidorIp();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		Mockito.when(transManager.connect()).thenReturn(con);
		servidores = new ArrayList<>();
		servidores.add(servidor);
	}

	@Test
	void testBuscarServidorPorId() throws PersistenceCustomException {
		Mockito.when(buscarServidorPort.buscarServidorPorId(con, "test")).thenReturn(servidor);
		Servidor respuesta = servidorService.buscarServidorPorId("test");
		
		assertNotNull(respuesta);
		assertEquals(servidor, respuesta);
		
		try {
			Mockito.when(buscarServidorPort.buscarServidorPorId(con, "test")).thenThrow(PersistenceCustomException.class);
			servidorService.buscarServidorPorId("test");
		}catch(ApplicationException e) {
			assertTrue(true);
		}
	}

	@Test
	void testBuscarServidorPorNombre() throws PersistenceCustomException {
		Mockito.when(buscarServidorPort.buscarServidorPorNombre(con, servidor)).thenReturn(servidores);
		List<Servidor> respuesta = servidorService.buscarServidorPorNombre(buscadorServidorNombre);
		
		assertNotNull(respuesta);
		assertEquals(servidores, respuesta);
		assertFalse(respuesta.isEmpty());
		assertEquals(1, respuesta.size());
		
		try {
			Mockito.when(buscarServidorPort.buscarServidorPorNombre(con, servidor)).thenThrow(PersistenceCustomException.class);
			servidorService.buscarServidorPorNombre(buscadorServidorNombre);
		}catch(ApplicationException e) {
			assertTrue(true);
		}
	}

	@Test
	void testBuscarServidorPorIp() throws PersistenceCustomException {
		try {
			Mockito.when(buscarServidorPort.buscarServidorPorIp(con, servidor)).thenReturn(servidores);
			List<Servidor> respuesta = servidorService.buscarServidorPorIp(buscadorServidorIp);
			
			assertNotNull(respuesta);
			assertEquals(servidores, respuesta);
			assertFalse(respuesta.isEmpty());
			assertEquals(1, respuesta.size());
			
			Mockito.when(buscarServidorPort.buscarServidorPorIp(con, servidor)).thenThrow(PersistenceCustomException.class);
			servidorService.buscarServidorPorIp(buscadorServidorIp);
		}catch(ApplicationException e) {
			assertTrue(true);
		}
	}

	@Test
	void testBuscarTodos() throws PersistenceCustomException {
		try {
			Mockito.when(buscarServidorPort.buscarTodos(con)).thenReturn(servidores);
			List<Servidor> respuesta = servidorService.buscarTodos();
			
			assertNotNull(respuesta);
			assertEquals(servidores, respuesta);
			assertFalse(respuesta.isEmpty());
			assertEquals(1, respuesta.size());
			
			Mockito.when(buscarServidorPort.buscarTodos(con)).thenThrow(PersistenceCustomException.class);
			servidorService.buscarTodos();
		}catch(ApplicationException e) {
			assertTrue(true);
		}
	}

	@Test
	void testBuscarServidorPorOs() throws PersistenceCustomException {
		try {
			Mockito.when(buscarServidorPort.buscarServidorPorOs(con, servidor)).thenReturn(servidores);
			List<Servidor> respuesta = servidorService.buscarServidorPorOs(buscadorServidorOs);
			
			assertNotNull(respuesta);
			assertEquals(servidores, respuesta);
			assertFalse(respuesta.isEmpty());
			assertEquals(1, respuesta.size());
			
			Mockito.when(buscarServidorPort.buscarServidorPorIp(con, servidor)).thenThrow(PersistenceCustomException.class);
			servidorService.buscarServidorPorOs(buscadorServidorOs);
		}catch(ApplicationException e) {
			assertTrue(true);
		}
	}

}
