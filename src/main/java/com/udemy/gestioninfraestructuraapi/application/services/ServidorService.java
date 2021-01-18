package com.udemy.gestioninfraestructuraapi.application.services;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.innermodel.BuscarPorId;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServidorService implements BuscarServidorUseCase {

	@Autowired
	private BuscarServidorPort buscarServidorPort;

    @Override
    public Servidor buscarServidorPorId(BuscarPorId buscarPorId) throws ApplicationException {
    	Servidor servidorRespuesta;
    	Servidor servidorEnviado = new Servidor();
    	servidorEnviado.setId(Integer.parseInt(buscarPorId.getId()));

    	try {
    		servidorRespuesta = buscarServidorPort.buscarServidorPorId(servidorEnviado);
    	} catch(PersistenceCustomException e) {
    		throw new ApplicationException(e.getMessage(), e);
    	}
    	
    	return servidorRespuesta;
    }

    @Override
    public List<Servidor> buscarTodos() throws ApplicationException {
    	List<Servidor> servidoresRespuesta;
    	
    	try {
    		servidoresRespuesta = buscarServidorPort.buscarTodos();
    	} catch(PersistenceCustomException e) {
			throw new ApplicationException(e.getMessage(), e);
		}
    	
    	return servidoresRespuesta;
    }
}
