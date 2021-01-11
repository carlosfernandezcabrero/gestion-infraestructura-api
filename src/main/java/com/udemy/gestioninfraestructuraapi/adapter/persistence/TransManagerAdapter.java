package com.udemy.gestioninfraestructuraapi.adapter.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import org.springframework.stereotype.Component;

import com.udemy.gestioninfraestructuraapi.application.port.TransManager;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;

@Component
class TransManagerAdapter implements TransManager {

	private Connection connection;
	
	@Override
	public Connection connect() throws PersistenceCustomException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestionInfraestructura",
													 "developer",
													 "developer");
		} catch(SQLException | ClassNotFoundException e) {
			throw new PersistenceCustomException(e.getMessage(), e);
		}
		
		return connection;
	}

	@Override
	public void close() throws PersistenceCustomException {
		try {
			if(!connection.isClosed()) {
				connection.close();
			}
		} catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage(), e);
		}
	}

	@Override
	public void closeFinally() {
		try {
			this.close();
		} catch(PersistenceCustomException e){
			throw new ApplicationException(e.getMessage(), e);
		}
	}

}
