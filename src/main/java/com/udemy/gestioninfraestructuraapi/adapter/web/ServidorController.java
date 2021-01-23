package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorPorCodigoUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase.CrearServidor;
import com.udemy.gestioninfraestructuraapi.exception.NotFoundException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/servidor")
class ServidorController {

	private final BuscarServidorPorCodigoUseCase buscarServidorPorCodigoUseCase;
	private final BuscarTodosServidorUseCase buscarTodosServidorUseCase;
	private final CrearServidorUseCase crearServidorUseCase;

	@Autowired
	public ServidorController(BuscarServidorPorCodigoUseCase buscarServidorPorCodigoUseCase,
							  BuscarTodosServidorUseCase buscarTodosServidorUseCase,
							  CrearServidorUseCase crearServidorUseCase){
		this.buscarServidorPorCodigoUseCase = buscarServidorPorCodigoUseCase;
		this.buscarTodosServidorUseCase = buscarTodosServidorUseCase;
		this.crearServidorUseCase = crearServidorUseCase;
	}

	/***
	 * Punto de entrada para obtener todos los Servidores
	 * @return ResponseEntity de List de Servidor y HttpStatus OK
	 */
	@GetMapping("/")
	public ResponseEntity<List<Servidor>> buscarTodos() {
		List<Servidor> servidores = buscarTodosServidorUseCase.buscarTodos();
		if (servidores.isEmpty()) {
			throw new NotFoundException();
		}
		return new ResponseEntity<>(servidores, HttpStatus.OK);
	}

	/***
	 * Punto de entrada para obtener un Servidor por su codigo
	 * @param codigo - codigo identificador de cada servidor
	 * @return ResponseEntity de Servidor y HttpStatus OK
	 */
	@GetMapping("/buscarPorCodigo/{codigo}")
	public ResponseEntity<Servidor> buscarPorCodigo(@PathVariable String codigo) {
		Servidor servidor = buscarServidorPorCodigoUseCase.buscarServidorPorCodigo(codigo);
		if (servidor == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<>(servidor, HttpStatus.OK);
	}

	/***
	 * Punto de entrada para crear un nuevo Servidor
	 * @param crearServidor - Objeto para validar los datos necesarios para crear un servidor
	 * @param bindingResult - Validador spring boot
	 * @return Boolean (true en el caso de que se cree y false en el caso contrario) y HttpStatus CREATED
	 */
	@PostMapping("/create")
	public ResponseEntity<Boolean> crear(@Valid @RequestBody CrearServidor crearServidor,
										 BindingResult bindingResult) {
		boolean respuesta;
		StringBuilder stringBuilder = new StringBuilder();
		if (bindingResult.hasErrors()) {
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				FieldError fe = (FieldError) objectError;
				stringBuilder.append(fe.getField()).append(": ").append(fe.getDefaultMessage()).append(" (").append(fe.getRejectedValue()).append(")|");
			}
			throw new ValidationException(stringBuilder.toString());
		} else {
			respuesta = crearServidorUseCase.crear(crearServidor);
			if(respuesta){
				return new ResponseEntity<>(true, HttpStatus.CREATED);
			}else{
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
