package com.udemy.gestioninfraestructuraapi.application.services;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorNombreUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosGrupoResolutorUseCase;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

import org.springframework.stereotype.Service;

@Service
class GrupoResolutorService implements BuscarGrupoResolutorPorNombreUseCase, BuscarTodosGrupoResolutorUseCase {

    @Override
    public List<GrupoResolutor> buscarTodos() throws ApplicationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GrupoResolutor buscarPorNombre(String nombre) throws ApplicationException {
        // TODO Auto-generated method stub
        return null;
    }

}
