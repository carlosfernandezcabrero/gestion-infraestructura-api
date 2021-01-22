package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorPorCodigoUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarTodosServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase.CrearServidor;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
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
	 * @throws ControllerException
	 */
	@GetMapping("/")
	public ResponseEntity<List<Servidor>> buscarTodos() throws ControllerException {
		List<Servidor> servidores;

		try {
			servidores = buscarTodosServidorUseCase.buscarTodos();
		} catch (ApplicationException e) {
			throw new ControllerException(e.getMessage(), e);
		}

		return new ResponseEntity<>(servidores, HttpStatus.OK);
	}

	/***
	 * Punto de entrada para obtener un Servidor por su codigo
	 * @param codigo
	 * @return ResponseEntity de Servidor y HttpStatus OK
	 * @throws ControllerException
	 */
	@GetMapping("/buscarPorCodigo/{codigo}")
	public ResponseEntity<Servidor> buscarPorCodigo(@PathVariable String codigo) throws ControllerException {
		Servidor servidor;

		try {
			servidor = buscarServidorPorCodigoUseCase.buscarServidorPorCodigo(codigo);
		} catch (ApplicationException e) {
			throw new ControllerException(e.getMessage(), e);
		}

		return new ResponseEntity<>(servidor, HttpStatus.OK);
	}

	/***
	 * Punto de entrada para crear un nuevo Servidor
	 * @param crearServidor
	 * @param bindingResult
	 * @return Boolean (true en el caso de que se cree y false en el caso contrario) y HttpStatus CREATED
	 * @throws ControllerException
	 */
	@PostMapping("/create")
	public ResponseEntity<Boolean> crear(@Valid @RequestBody CrearServidor crearServidor, BindingResult bindingResult)
			throws ControllerException {
		boolean respuesta;
		StringBuilder stringBuilder = new StringBuilder();

		try {
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
		} catch (ApplicationException e) {
			if (e.getCause() == null){
				throw new ControllerException(e.getMessage(), null);
			} else {
				throw new ControllerException(e.getMessage(), e);
			}
		}
	}
}
