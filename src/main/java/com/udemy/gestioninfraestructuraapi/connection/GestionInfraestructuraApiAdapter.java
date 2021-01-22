package com.udemy.gestioninfraestructuraapi.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

@Component
class GestionInfraestructuraApiAdapter implements TransManager {

	private Connection connection;

	@Override
	public Connection connect() throws PersistenceCustomException {
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "rdeveloper");
			properties.setProperty("password", "developer");
			properties.setProperty("MaxPooledStatements", "200");

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestionInfraestructuraApi", properties);
			connection.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistenceCustomException(e.getMessage(), e);
		}

		return connection;
	}

}
