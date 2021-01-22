package com.udemy.gestioninfraestructuraapi.application.services;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorPorCodigoUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarTodosGenericoPort;
import com.udemy.gestioninfraestructuraapi.application.port.CrearGenericoPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import com.udemy.gestioninfraestructuraapi.resource.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ServidorService implements BuscarServidorPorCodigoUseCase, BuscarTodosServidorUseCase, CrearServidorUseCase {

	private final BuscarServidorPort buscarServidorPort;
	private final BuscarTodosGenericoPort<Servidor> buscarTodosGenericoPort;
	private final CrearGenericoPort<Servidor> crearGenericoPort;

	@Autowired
	public ServidorService(BuscarServidorPort buscarServidorPort,
						   BuscarTodosGenericoPort<Servidor> buscarTodosGenericoPort,
						   CrearGenericoPort<Servidor> crearGenericoPort){
		this.buscarServidorPort = buscarServidorPort;
		this.buscarTodosGenericoPort = buscarTodosGenericoPort;
		this.crearGenericoPort = crearGenericoPort;
	}

	@Override
	public Servidor buscarServidorPorCodigo(String codigo) throws ApplicationException {
		Validator.validarNumeroEnteroLargo(codigo);
		Servidor servidorRespuesta;
		Servidor servidorEnviado = new Servidor();
		servidorEnviado.setCodigo(Long.parseLong(codigo));

		try {
			servidorRespuesta = buscarServidorPort.buscarServidorPorCodigo(servidorEnviado);
		} catch (PersistenceCustomException e) {
			throw new ApplicationException(e.getMessage(), e);
		}

		return servidorRespuesta;
	}

	@Override
	public List<Servidor> buscarTodos() throws ApplicationException {
		List<Servidor> servidoresRespuesta;

		try {
			servidoresRespuesta = buscarTodosGenericoPort.buscarTodos();
		} catch (PersistenceCustomException e) {
			throw new ApplicationException(e.getMessage(), e);
		}

		return servidoresRespuesta;
	}

	@Override
	public boolean crear(CrearServidor crearServidor) throws ApplicationException {
		Servidor servidorEnviado = new Servidor();
		servidorEnviado.setIp(crearServidor.getIp());
		servidorEnviado.setNombre(crearServidor.getNombre());
		servidorEnviado.setOs(crearServidor.getOs());
		servidorEnviado.setGrupoResolutor(crearServidor.getGrupoResolutor());
		
		boolean respuesta;
		
		try {
			respuesta = crearGenericoPort.crearGenerico(servidorEnviado);
		}catch(PersistenceCustomException e) {
			throw new ApplicationException(e.getMessage(), e);
		}
		
		return respuesta;
	}
}
