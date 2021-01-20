package com.udemy.gestioninfraestructuraapi.adapter.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.udemy.gestioninfraestructuraapi.connection.TransManager;
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
	@Autowired
	private TransManager transManager;

	@Override
	public Servidor buscarServidorPorId(Servidor servidor) throws PersistenceCustomException {
		Servidor servidorRes = null;
		Connection connection = transManager.connect();

		try (PreparedStatement st = connection.prepareStatement(DbQuerys.BUSCARPORCODIGO)) {
			st.setLong(1, servidor.getCodigo());

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				servidorRes = new Servidor();
				servidorRes.setCodigo(rs.getLong(COLUMNACODIGO));
				servidorRes.setNombre(rs.getString(COLUMNANOMBRE));
				servidorRes.setIp(rs.getString(COLUMNAIP));
				servidorRes.setOs(rs.getString(COLUMNAOS));
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
		Servidor servidor;
		Connection connection = transManager.connect();

		try (PreparedStatement st = connection.prepareStatement(DbQuerys.BUSCARTODOSSERVIDORES)) {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				servidor = new Servidor();
				servidor.setCodigo(rs.getLong(COLUMNACODIGO));
				servidor.setNombre(rs.getString(COLUMNANOMBRE));
				servidor.setIp(rs.getString(COLUMNAIP));
				servidor.setOs(rs.getString(COLUMNAOS));
				servidores.add(servidor);
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
}
