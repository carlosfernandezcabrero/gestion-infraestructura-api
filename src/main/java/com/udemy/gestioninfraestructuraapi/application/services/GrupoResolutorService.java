package com.udemy.gestioninfraestructuraapi.application.services;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorDescripcionUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorNombreUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosGrupoResolutorUseCase;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarGrupoResolutorPort;
import com.udemy.gestioninfraestructuraapi.application.port.BuscarTodosGenericoPort;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.PersistenceCustomException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class GrupoResolutorService implements BuscarGrupoResolutorPorNombreUseCase,
        BuscarTodosGrupoResolutorUseCase, BuscarGrupoResolutorPorDescripcionUseCase {

    private final BuscarTodosGenericoPort<GrupoResolutor> buscarTodosGenericoPort;
    private final BuscarGrupoResolutorPort buscarGrupoResolutorPort;

    @Autowired
    public GrupoResolutorService(BuscarTodosGenericoPort<GrupoResolutor> buscarTodosGenericoPort,
                                 BuscarGrupoResolutorPort buscarGrupoResolutorPort){
        this.buscarTodosGenericoPort = buscarTodosGenericoPort;
        this.buscarGrupoResolutorPort = buscarGrupoResolutorPort;
    }

    @Override
    public List<GrupoResolutor> buscarTodos() throws ApplicationException {
        List<GrupoResolutor> grupoResolutorList;

        try{
            grupoResolutorList = buscarTodosGenericoPort.buscarTodos();
        }catch(PersistenceCustomException e){
            throw new ApplicationException(e.getMessage(), e.getCause());
        }

        return grupoResolutorList;
    }

    @Override
    public List<GrupoResolutor> buscarPorNombre(String nombre) throws ApplicationException {
        List<GrupoResolutor> grupoResolutorRespuesta;
        GrupoResolutor grupoResolutorEnviado = new GrupoResolutor();
        grupoResolutorEnviado.setNombre(nombre);

        try{
            grupoResolutorRespuesta = buscarGrupoResolutorPort.buscarPorNombre(grupoResolutorEnviado);
        }catch(PersistenceCustomException e){
            throw new ApplicationException(e.getMessage(), e.getCause());
        }

        return grupoResolutorRespuesta;
    }

    @Override
    public List<GrupoResolutor> buscarGrupoResolutorPorDescripcion(String descripcion) {
        GrupoResolutor grupoResolutorEnviado = new GrupoResolutor();
        grupoResolutorEnviado.setDescripcion(descripcion);
        List<GrupoResolutor> grupoResolutorList;

        try{
            grupoResolutorList = buscarGrupoResolutorPort.buscarPorDescripcion(grupoResolutorEnviado);
        }catch(PersistenceCustomException e){
            throw new ApplicationException(e.getMessage(), e.getCause());
        }

        return grupoResolutorList;
    }
}
