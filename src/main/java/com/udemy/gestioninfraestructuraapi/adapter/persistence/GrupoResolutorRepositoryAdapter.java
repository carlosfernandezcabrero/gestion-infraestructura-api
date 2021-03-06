package com.udemy.gestioninfraestructuraapi.adapter.persistence;

import com.udemy.gestioninfraestructuraapi.application.port.BuscarGrupoResolutorPort;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarTodosGenericoPort;
import com.udemy.gestioninfraestructuraapi.connection.TransManager;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;
import com.udemy.gestioninfraestructuraapi.resource.DbQuerys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
class GrupoResolutorRepositoryAdapter implements BuscarGrupoResolutorPort, BuscarTodosGenericoPort<GrupoResolutor> {

    private static final String COLUMNA_NOMBRE = "nombre";
    private static final String COLUMNA_DESCRIPCION = "descripcion";
    private final TransManager transManager;

    @Autowired
    public GrupoResolutorRepositoryAdapter(TransManager transManager){
        this.transManager = transManager;
    }

    @Override
    public List<GrupoResolutor> buscarPorNombre(GrupoResolutor grupoResolutor) throws PersistenceCustomException {
        List<GrupoResolutor> grupoResolutorResp = new ArrayList<>();

        try(Connection connection = transManager.connect();
            PreparedStatement statement = connection.prepareStatement(DbQuerys.BUSCAR_GRUPORESOLUTOR_POR_NOMBRE)){
            statement.setString(1, "%" + grupoResolutor.getNombre() + "%");

            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    grupoResolutorResp.add(new GrupoResolutor(
                            resultSet.getString(COLUMNA_NOMBRE),
                            resultSet.getString(COLUMNA_DESCRIPCION)
                    ));
                }
            }
        }catch(SQLException e){
            throw new PersistenceCustomException(e.getMessage(), e);
        }

        return grupoResolutorResp;
    }

    @Override
    public List<GrupoResolutor> buscarTodos() throws PersistenceCustomException {
        List<GrupoResolutor> grupoResolutorList = new ArrayList<>();
        GrupoResolutor grupoResolutor;

        try(Connection connection = transManager.connect();
            PreparedStatement statement = connection.prepareStatement(DbQuerys.BUSCAR_TODOS_GRUPORESOLUTOR)){
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    grupoResolutor = new GrupoResolutor();
                    grupoResolutor.setNombre(resultSet.getString(COLUMNA_NOMBRE));
                    grupoResolutor.setDescripcion(resultSet.getString(COLUMNA_DESCRIPCION));
                    grupoResolutorList.add(grupoResolutor);
                }
            }
        }catch(SQLException e){
            throw new PersistenceCustomException(e.getMessage(), e);
        }

        return grupoResolutorList;
    }

	@Override
	public GrupoResolutor buscarPorId(String grupoResolutor) throws PersistenceCustomException {
		GrupoResolutor grupoResolutorObject = null;
		
		try(Connection connection = transManager.connect();
			PreparedStatement statement = connection.prepareStatement(DbQuerys.BUSCAR_GRUPORESOLUTOR_POR_ID)){
			statement.setString(1, grupoResolutor);
			
			try(ResultSet rs = statement.executeQuery()){
				if(rs.next()) {
					grupoResolutorObject = new GrupoResolutor();
					grupoResolutorObject.setNombre(rs.getString(COLUMNA_NOMBRE));
                    grupoResolutorObject.setDescripcion(rs.getString(COLUMNA_DESCRIPCION));
				}
			}
		}catch(SQLException e) {
			throw new PersistenceCustomException(e.getMessage(), e);
		}
		
		return grupoResolutorObject;
	}

    @Override
    public List<GrupoResolutor> buscarPorDescripcion(GrupoResolutor grupoResolutor) throws PersistenceCustomException {
        List<GrupoResolutor> grupoResolutorList = new ArrayList<>();

        try(Connection connection = transManager.connect();
            PreparedStatement statement = connection.prepareStatement(DbQuerys.BUSCAR_GRUPORESOLUTOR_POR_DESCRIPCION)){
            statement.setString(1, "%" + grupoResolutor.getDescripcion() + "%");

            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    grupoResolutorList.add(new GrupoResolutor(
                            resultSet.getString(COLUMNA_NOMBRE),
                            resultSet.getString(COLUMNA_DESCRIPCION)
                    ));
                }
            }
        }catch(SQLException e) {
            throw new PersistenceCustomException(e.getMessage(), e);
        }

        return grupoResolutorList;
    }
}
