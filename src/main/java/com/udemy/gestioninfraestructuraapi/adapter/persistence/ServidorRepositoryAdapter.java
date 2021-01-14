package com.udemy.gestioninfraestructuraapi.adapter.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import com.udemy.gestioninfraestructuraapi.resource.DbQuerys;

@Component
class ServidorRepositoryAdapter implements BuscarServidorPort {
	
	private static final String COLUMNANOMBRE = "nombre";
	private static final String COLUMNAGRUPORESOLUTOR = "fk_grupo_resolutor";
	private static final String COLUMNAIP = "ip";
	private static final String COLUMNAOS = "os";
	private static final String COLUMNACODIGO = "codigo";
	
	@Override
	public Servidor buscarServidorPorId(Connection con, String id) throws PersistenceCustomException {
		Servidor servidor = null;
		
		try(PreparedStatement st = con.prepareStatement(DbQuerys.BUSCARPORCODIGO)) {
			st.setString(1, id);
			
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				servidor = new Servidor(
							rs.getInt(COLUMNACODIGO),
							rs.getString(COLUMNANOMBRE),
							rs.getString(COLUMNAIP),
							new GrupoResolutor(rs.getString(COLUMNAGRUPORESOLUTOR)),
							rs.getString(COLUMNAOS)
							);
			}
		} catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage()	, e);
		}
		
		return servidor;
	}
	
	@Override
	public List<Servidor> buscarServidorPorNombre(Connection con, Servidor servidor) throws PersistenceCustomException {
		List<Servidor> servidores = new ArrayList<>();
		
		try(PreparedStatement st = con.prepareStatement(DbQuerys.BUSCARPORNOMBRE)) {
			st.setString(1, "%" + servidor.getNombre() + "%");
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				servidores.add(new Servidor(
											rs.getInt(COLUMNACODIGO),
											rs.getString(COLUMNANOMBRE),
											rs.getString(COLUMNAIP),
											new GrupoResolutor(rs.getString(COLUMNAGRUPORESOLUTOR)),
											rs.getString(COLUMNAOS)
											));
			}
		} catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage()	, e);
		}
		
		return servidores;
	}
	
	@Override
	public List<Servidor> buscarServidorPorIp(Connection con, Servidor servidor) throws PersistenceCustomException {
		List<Servidor> servidores = new ArrayList<>();
		
		try(PreparedStatement st = con.prepareStatement(DbQuerys.BUSCARPORIP)) {
			st.setString(1, "%" + servidor.getIp() + "%");
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				servidores.add(new Servidor(
											rs.getInt(COLUMNACODIGO),
											rs.getString(COLUMNANOMBRE),
											rs.getString(COLUMNAIP),
											new GrupoResolutor(rs.getString(COLUMNAGRUPORESOLUTOR)),
											rs.getString(COLUMNAOS)
											));
			}
		} catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage()	, e);
		}
		
		return servidores;
	}
	
	@Override
	public List<Servidor> buscarTodos(Connection con) throws PersistenceCustomException {
		List<Servidor> servidores = new ArrayList<>();
		
		try(PreparedStatement st = con.prepareStatement(DbQuerys.BUSCARTODOSSERVIDORES)) {
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				servidores.add(new Servidor(
											rs.getInt(COLUMNACODIGO),
											rs.getString(COLUMNANOMBRE),
											rs.getString(COLUMNAIP),
											new GrupoResolutor(rs.getString(COLUMNAGRUPORESOLUTOR)),
											rs.getString(COLUMNAOS)
											));
			}
		} catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage()	, e);
		}
		
		return servidores;
	}
	

	@Override
	public List<Servidor> buscarServidorPorOs(Connection con, Servidor servidor) throws PersistenceCustomException {
		List<Servidor> servidores = new ArrayList<>();
		
		try(PreparedStatement st = con.prepareStatement(DbQuerys.BUSCARPOROS)) {
			st.setString(1, "%" + servidor.getOs() + "%");
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				servidores.add(new Servidor(
											rs.getInt(COLUMNACODIGO),
											rs.getString(COLUMNANOMBRE),
											rs.getString(COLUMNAIP),
											new GrupoResolutor(rs.getString(COLUMNAGRUPORESOLUTOR)),
											rs.getString(COLUMNAOS)
											));
			}
		} catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage()	, e);
		}
		
		return servidores;
	}

}
