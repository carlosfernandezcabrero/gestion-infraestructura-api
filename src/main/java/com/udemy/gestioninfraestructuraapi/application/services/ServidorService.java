package com.udemy.gestioninfraestructuraapi.application.services;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorPorCodigoUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import com.udemy.gestioninfraestructuraapi.resource.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ServidorService implements BuscarServidorPorCodigoUseCase, BuscarTodosServidorUseCase, CrearServidorUseCase {

	@Autowired
	private BuscarServidorPort buscarServidorPort;

	@Override
	public Servidor buscarServidorPorCodigo(String codigo) throws ApplicationException {
		Validator.validarNumeroEnteroLargo(codigo);
		Servidor servidorRespuesta;
		Servidor servidorEnviado = new Servidor();
		servidorEnviado.setCodigo(Long.parseLong(codigo));

		try {
			servidorRespuesta = buscarServidorPort.buscarServidorPorId(servidorEnviado);
		} catch (PersistenceCustomException e) {
			throw new ApplicationException(e.getMessage(), e);
		}

		return servidorRespuesta;
	}

	@Override
	public List<Servidor> buscarTodos() throws ApplicationException {
		List<Servidor> servidoresRespuesta;

		try {
			servidoresRespuesta = buscarServidorPort.buscarTodos();
		} catch (PersistenceCustomException e) {
			throw new ApplicationException(e.getMessage(), e);
		}

		return servidoresRespuesta;
	}

	@Override
	public Servidor crear(CrearServidor crearServidor) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
}
