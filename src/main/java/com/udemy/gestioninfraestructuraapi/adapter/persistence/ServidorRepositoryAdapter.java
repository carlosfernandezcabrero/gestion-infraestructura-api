package com.udemy.gestioninfraestructuraapi.adapter.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarTodosGenericoPort;
import com.udemy.gestioninfraestructuraapi.application.port.CrearGenericoPort;
import com.udemy.gestioninfraestructuraapi.connection.TransManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarServidorPort;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import com.udemy.gestioninfraestructuraapi.resource.DbQuerys;

@Component
class ServidorRepositoryAdapter implements BuscarServidorPort, BuscarTodosGenericoPort<Servidor>,
		CrearGenericoPort<Servidor> {

	private static final String COLUMNA_NOMBRE = "nombre";
	private static final String COLUMNA_IP = "ip";
	private static final String COLUMNA_OS = "os";
	private static final String COLUMNA_CODIGO = "codigo";
	private static final String COLUMNA_GRUPORESOLUTOR = "fk_grupo_resolutor";
	private final TransManager transManager;

	@Autowired
	public ServidorRepositoryAdapter(TransManager transManager){
		this.transManager = transManager;
	}

	@Override
	public Servidor buscarServidorPorCodigo(Servidor servidor) throws PersistenceCustomException {
		Servidor servidorRes = null;
		Connection connection = transManager.connect();

		try (PreparedStatement st = connection.prepareStatement(DbQuerys.BUSCAR_POR_CODIGO)) {
			st.setLong(1, servidor.getCodigo());

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				servidorRes = new Servidor();
				servidorRes.setCodigo(rs.getLong(COLUMNA_CODIGO));
				servidorRes.setNombre(rs.getString(COLUMNA_NOMBRE));
				servidorRes.setIp(rs.getString(COLUMNA_IP));
				servidorRes.setOs(rs.getString(COLUMNA_OS));
				servidorRes.setGrupoResolutor(rs.getString(COLUMNA_GRUPORESOLUTOR));
			}
		} catch (SQLException e) {
			throw new PersistenceCustomException(e.getMessage(), e);
		} finally {
			if (connection != null) {
				transManager.closeFinally();
			}
		}

		return servidorRes;
	}

	@Override
	public List<Servidor> buscarTodos() throws PersistenceCustomException {
		List<Servidor> servidores = new ArrayList<>();
		Servidor servidorRes;
		Connection connection = transManager.connect();

		try (PreparedStatement st = connection.prepareStatement(DbQuerys.BUSCAR_TODOS_SERVIDORES)) {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				servidorRes = new Servidor();
				servidorRes.setCodigo(rs.getLong(COLUMNA_CODIGO));
				servidorRes.setNombre(rs.getString(COLUMNA_NOMBRE));
				servidorRes.setIp(rs.getString(COLUMNA_IP));
				servidorRes.setOs(rs.getString(COLUMNA_OS));
				servidorRes.setGrupoResolutor(rs.getString(COLUMNA_GRUPORESOLUTOR));
				servidores.add(servidorRes);
			}
		} catch (SQLException e) {
			throw new PersistenceCustomException(e.getMessage(), e);
		} finally {
			if (connection != null) {
				transManager.closeFinally();
			}
		}

		return servidores;
	}

	@Override
	public boolean crearGenerico(Servidor servidor) throws PersistenceCustomException {
		Connection connection = transManager.connect();
		boolean respuesta;

		try(PreparedStatement statement = connection.prepareStatement(DbQuerys.CREAR_SERVIDOR)){
			statement.setString(1, servidor.getNombre());
			statement.setString(2, servidor.getIp());
			statement.setString(3, servidor.getOs());
			statement.setString(4, servidor.getGrupoResolutor());

			respuesta = statement.executeUpdate() > 0;
		}catch(SQLException e){
			throw new PersistenceCustomException(e.getMessage(), e);
		} finally {
			if (connection != null) {
				transManager.closeFinally();
			}
		}

		return respuesta;
	}
}
