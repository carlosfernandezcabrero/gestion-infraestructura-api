package com.udemy.gestioninfraestructuraapi.application.services;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.application.port.TransManager;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class ServidorService implements BuscarServidorUseCase {

	@Autowired
    private TransManager transManager;
	@Autowired
	private BuscarServidorPort buscarServidorPort;

    @Override
    public Servidor buscarServidorPorId(String id) {
    	Servidor servidor;
    	Connection con = null;
    	
    	try {
    		con = transManager.connect();
    		
    		servidor = buscarServidorPort.buscarServidorPorId(con, id);
    	} catch(PersistenceCustomException e) {
    		throw new ApplicationException(e.getMessage(), e);
    	} finally {
    		if(con != null) {
    			transManager.closeFinally();
    		}
    	}
    	
    	return servidor;
    }

    @Override
    public List<Servidor> buscarServidorPorNombre(BuscadorServidorNombre nombre) {
    	List<Servidor> servidores;
    	Connection con = null;
    	Servidor servidor;
    	
    	try {
    		servidor = new Servidor();
    		servidor.setNombre(nombre.getNombre());
    		con = transManager.connect();
    		servidores = buscarServidorPort.buscarServidorPorNombre(con, servidor);
    	} catch(PersistenceCustomException e) {
    		throw new ApplicationException(e.getMessage(), e);
    	} finally {
    		if(con != null) {
    			transManager.closeFinally();
    		}
    	}
    	
    	return servidores;
    }

    @Override
    public List<Servidor> buscarServidorPorIp(BuscadorServidorIp ip) {
    	List<Servidor> servidores;
    	Connection con = null;
    	Servidor servidor;
    	
    	try {
    		servidor = new Servidor();
    		servidor.setIp(ip.getIp());
    		con = transManager.connect();
    		servidores = buscarServidorPort.buscarServidorPorIp(con, servidor);
    	} catch(PersistenceCustomException e) {
    		throw new ApplicationException(e.getMessage(), e);
    	} finally {
    		if(con != null) {
    			transManager.closeFinally();
    		}
    	}
    	
    	return servidores;
    }

    @Override
    public List<Servidor> buscarTodos() {
    	List<Servidor> servidores;
    	Connection con = null;
    	
    	try {
    		con = transManager.connect();
    		
    		servidores = buscarServidorPort.buscarTodos(con);
    	} catch(PersistenceCustomException e) {
    		throw new ApplicationException(e.getMessage(), e);
    	} finally {
    		if(con != null) {
    			transManager.closeFinally();
    		}
    	}
    	
    	return servidores;
    }
    

	@Override
	public List<Servidor> buscarServidorPorOs(BuscadorServidorOs os) {
		List<Servidor> servidores;
    	Connection con = null;
    	Servidor servidor;
    	
    	try {
    		servidor = new Servidor();
    		servidor.setOs(os.getOs());
    		con = transManager.connect();
    		servidores = buscarServidorPort.buscarServidorPorOs(con, servidor);
    	} catch(PersistenceCustomException e) {
    		throw new ApplicationException(e.getMessage(), e);
    	} finally {
    		if(con != null) {
    			transManager.closeFinally();
    		}
    	}
    	
    	return servidores;
	}

}
