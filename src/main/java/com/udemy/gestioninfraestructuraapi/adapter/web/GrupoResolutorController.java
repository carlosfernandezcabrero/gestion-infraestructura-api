package com.udemy.gestioninfraestructuraapi.adapter.web;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarGrupoResolutorPorDescripcionUseCase;
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
    private final BuscarGrupoResolutorPorDescripcionUseCase buscarGrupoResolutorPorDescripcionUseCase;

    @Autowired
    public GrupoResolutorController(BuscarTodosGrupoResolutorUseCase buscarTodosGrupoResolutorUseCase,
                                    BuscarGrupoResolutorPorNombreUseCase buscarGrupoResolutorPorNombreUseCase,
                                    BuscarGrupoResolutorPorDescripcionUseCase buscarGrupoResolutorPorDescripcionUseCase){
        this.buscarTodosGrupoResolutorUseCase = buscarTodosGrupoResolutorUseCase;
        this.buscarGrupoResolutorPorNombreUseCase = buscarGrupoResolutorPorNombreUseCase;
        this.buscarGrupoResolutorPorDescripcionUseCase = buscarGrupoResolutorPorDescripcionUseCase;
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
    public ResponseEntity<List<GrupoResolutor>> buscarPorNombre(@RequestParam String nombre) {
        List<GrupoResolutor> grupoResolutor = buscarGrupoResolutorPorNombreUseCase.buscarPorNombre(nombre);
        if (grupoResolutor.isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>(grupoResolutor, HttpStatus.OK);
    }

    /***
     * Punto de entrada para buscar grupos resolutores por su descripcion
     * @param descripcion - cadena que debe tener la descripcion
     * @return ResponseEntity de List de GrupoResolutor y HttpStatus OK
     */
    @GetMapping("/buscarPorDescripcion")
    public ResponseEntity<List<GrupoResolutor>> buscarPorDescripcion(@RequestParam String descripcion){
        List<GrupoResolutor> grupoResolutorList = buscarGrupoResolutorPorDescripcionUseCase.buscarGrupoResolutorPorDescripcion(descripcion);
        if (grupoResolutorList.isEmpty()){
            throw new NotFoundException();
        }
        return new ResponseEntity<>(grupoResolutorList, HttpStatus.OK);
    }

}
