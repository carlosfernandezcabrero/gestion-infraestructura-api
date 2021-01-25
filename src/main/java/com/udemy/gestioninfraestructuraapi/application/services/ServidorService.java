package com.udemy.gestioninfraestructuraapi.application.services;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorPorCodigoUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarGrupoResolutorPort;
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
	private final BuscarGrupoResolutorPort buscarGrupoResolutor;

	@Autowired
	public ServidorService(BuscarServidorPort buscarServidorPort,
						   BuscarTodosGenericoPort<Servidor> buscarTodosGenericoPort,
						   CrearGenericoPort<Servidor> crearGenericoPort,
						   BuscarGrupoResolutorPort buscarGrupoResolutor){
		this.buscarServidorPort = buscarServidorPort;
		this.buscarTodosGenericoPort = buscarTodosGenericoPort;
		this.crearGenericoPort = crearGenericoPort;
		this.buscarGrupoResolutor = buscarGrupoResolutor;
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
			throw new ApplicationException(e.getMessage(), e.getCause());
		}

		return servidorRespuesta;
	}

	@Override
	public List<Servidor> buscarTodos() throws ApplicationException {
		List<Servidor> servidoresRespuesta;

		try {
			servidoresRespuesta = buscarTodosGenericoPort.buscarTodos();
		} catch (PersistenceCustomException e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		}

		return servidoresRespuesta;
	}

	@Override
	public boolean crear(CrearServidor crearServidor) throws ApplicationException {
		Servidor servidorEnviado = new Servidor();
		servidorEnviado.setIp(crearServidor.getIp().trim());
		servidorEnviado.setNombre(crearServidor.getNombre().trim());
		servidorEnviado.setOs(crearServidor.getOs().trim());
		servidorEnviado.setGrupoResolutor(crearServidor.getGrupoResolutor().trim());
		
		boolean respuesta;
		
		try {
			requireExistGrupoResolutor(crearServidor.getGrupoResolutor());
			respuesta = crearGenericoPort.crearGenerico(servidorEnviado);
		}catch(PersistenceCustomException | NoExistGrupoResolutor e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		}
		
		return respuesta;
	}
	
	/***
	 * Metodo que comprueba si existe el grupo resolutor especificado para el servidor
	 * @param grupoResolutor - nombre del grupo resolutor
	 * @throws PersistenceCustomException - lanza excepciones de la capa de persistencia
	 */
	private void requireExistGrupoResolutor(String grupoResolutor) throws PersistenceCustomException {
		if (buscarGrupoResolutor.buscarPorId(grupoResolutor) == null) {
			throw new NoExistGrupoResolutor();
		}
	}
}
