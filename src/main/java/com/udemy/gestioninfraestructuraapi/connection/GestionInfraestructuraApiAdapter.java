package com.udemy.gestioninfraestructuraapi.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

@Component
class GestionInfraestructuraApiAdapter implements TransManager {

	private Connection connection;

	@Override
	public Connection connect() throws PersistenceCustomException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestionInfraestructuraApi",
					"rdeveloper", "developer");
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistenceCustomException(e.getMessage(), e);
		}

		return connection;
	}

	private void close() throws PersistenceCustomException {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new PersistenceCustomException(e.getMessage(), e);
		}
	}

	@Override
	public void closeFinally() throws PersistenceCustomException {
		this.close();
	}

}
