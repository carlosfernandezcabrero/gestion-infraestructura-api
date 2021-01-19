package com.udemy.gestioninfraestructuraapi.adapter.web;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorNombreUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosGrupoResolutorUseCase;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupo-resolutor")
class GrupoResolutorController {

    @Autowired
    private BuscarTodosGrupoResolutorUseCase buscarTodosGrupoResolutorUseCase;
    @Autowired
    private BuscarGrupoResolutorPorNombreUseCase buscarGrupoResolutorPorNombreUseCase;

    @GetMapping("/")
    public ResponseEntity<List<GrupoResolutor>> todos() throws ControllerException {
        List<GrupoResolutor> gruposResolutores;

        try {
            gruposResolutores = buscarTodosGrupoResolutorUseCase.buscarTodos();
        } catch (ApplicationException e) {
            throw new ControllerException(e.getMessage(), e);
        }

        return new ResponseEntity<>(gruposResolutores, HttpStatus.OK);
    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<GrupoResolutor> buscarPorNombre(@PathVariable String nombre) throws ControllerException {
        GrupoResolutor grupoResolutor = null;

        try {
            grupoResolutor = buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(nombre);
        } catch (ApplicationException e) {
            throw new ControllerException(e.getMessage(), e);
        }

        return new ResponseEntity<>(grupoResolutor, HttpStatus.OK);
    }

}
