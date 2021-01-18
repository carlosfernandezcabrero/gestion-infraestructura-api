package com.udemy.gestioninfraestructuraapi.adapter.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.udemy.gestioninfraestructuraapi.connection.TransManager;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import com.udemy.gestioninfraestructuraapi.resource.DbQuerys;

@Component
class ServidorRepositoryAdapter implements BuscarServidorPort {
	
	private static final String COLUMNANOMBRE = "nombre";
	private static final String COLUMNAIP = "ip";
	private static final String COLUMNAOS = "os";
	private static final String COLUMNACODIGO = "codigo";
	private Connection connection;
	@Autowired
	private TransManager transManager;

	public ServidorRepositoryAdapter() throws ApplicationException {
		try {
			connection = transManager.connect();
		}catch (PersistenceCustomException e){
			throw new ApplicationException(e.getMessage(), e);
		}
	}
	
	@Override
	public Servidor buscarServidorPorId(Servidor servidor) throws PersistenceCustomException {
		Servidor servidorRes = null;
		
		try(PreparedStatement st = connection.prepareStatement(DbQuerys.BUSCARPORCODIGO)) {
			st.setInt(1, servidor.getId());
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				servidorRes = new Servidor(
							rs.getInt(COLUMNACODIGO),
							rs.getString(COLUMNANOMBRE),
							rs.getString(COLUMNAIP),
							rs.getString(COLUMNAOS)
							);
			}
		} catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage()	, e);
		} finally {
			if(connection != null){
				transManager.closeFinally();
			}
		}
		
		return servidorRes;
	}
	
	@Override
	public List<Servidor> buscarTodos() throws PersistenceCustomException {
		List<Servidor> servidores = new ArrayList<>();
		
		try(PreparedStatement st = connection.prepareStatement(DbQuerys.BUSCARTODOSSERVIDORES)) {
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				servidores.add(new Servidor(
											rs.getInt(COLUMNACODIGO),
											rs.getString(COLUMNANOMBRE),
											rs.getString(COLUMNAIP),
											rs.getString(COLUMNAOS)
											));
			}
		} catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage()	, e);
		} finally {
			if(connection != null) {
				transManager.closeFinally();
			}
		}
		
		return servidores;
	}
}
