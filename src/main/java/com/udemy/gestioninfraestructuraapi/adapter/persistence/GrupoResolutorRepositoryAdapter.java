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
    public GrupoResolutor buscarPorNombre(GrupoResolutor grupoResolutor) throws PersistenceCustomException {
        GrupoResolutor grupoResolutorResp = null;

        try(Connection connection = transManager.connect();
            PreparedStatement statement = connection.prepareStatement(DbQuerys.BUSCAR_POR_NOMBRE)){
            statement.setString(1, grupoResolutor.getNombre());

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    grupoResolutorResp = new GrupoResolutor();
                    grupoResolutorResp.setNombre(resultSet.getString(COLUMNA_NOMBRE));
                    grupoResolutorResp.setDescripcion(resultSet.getString(COLUMNA_DESCRIPCION));
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
}
