package com.udemy.gestioninfraestructuraapi.adapter.web;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorNombreUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosGrupoResolutorUseCase;
import com.udemy.gestioninfraestructuraapi.exception.NotFoundException;
import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupo-resolutor")
class GrupoResolutorController {

    private final BuscarTodosGrupoResolutorUseCase buscarTodosGrupoResolutorUseCase;
    private final BuscarGrupoResolutorPorNombreUseCase buscarGrupoResolutorPorNombreUseCase;

    @Autowired
    public GrupoResolutorController(BuscarTodosGrupoResolutorUseCase buscarTodosGrupoResolutorUseCase,
                                    BuscarGrupoResolutorPorNombreUseCase buscarGrupoResolutorPorNombreUseCase){
        this.buscarTodosGrupoResolutorUseCase = buscarTodosGrupoResolutorUseCase;
        this.buscarGrupoResolutorPorNombreUseCase = buscarGrupoResolutorPorNombreUseCase;
    }

    /***
     * Punto de entrada para obtener todos los Grupos Resolutores
     * @return ResponseEntity de List de GrupoResolutor y HttpStatus OK
     */
    @GetMapping("/")
    public ResponseEntity<List<GrupoResolutor>> todos() {
        List<GrupoResolutor> gruposResolutores = buscarTodosGrupoResolutorUseCase.buscarTodos();
        if (gruposResolutores.isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(gruposResolutores, HttpStatus.OK);
    }

    /***
     * Punto de entrada para obtener un Grupo Resolutor en base a su nombre
     * @param nombre - cadena que debe contener en el nombre el grupo resolutor buscado
     * @return ResponseEntity de GrupoResolutor y HttpStatus OK
     */
    @GetMapping("/buscarPorNombre")
    public ResponseEntity<GrupoResolutor> buscarPorNombre(@RequestParam String nombre) {
        GrupoResolutor grupoResolutor = buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(nombre);
        if (grupoResolutor == null) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(grupoResolutor, HttpStatus.OK);
    }

}
