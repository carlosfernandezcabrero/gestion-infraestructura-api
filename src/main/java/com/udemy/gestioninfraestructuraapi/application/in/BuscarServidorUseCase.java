package com.udemy.gestioninfraestructuraapi.application.in;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.innermodel.BuscarPorId;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import org.springframework.stereotype.Component;

@Component
public interface BuscarServidorUseCase {

    Servidor buscarServidorPorId(BuscarPorId buscarPorId) throws ApplicationException;
    List<Servidor> buscarTodos() throws ApplicationException;
}
