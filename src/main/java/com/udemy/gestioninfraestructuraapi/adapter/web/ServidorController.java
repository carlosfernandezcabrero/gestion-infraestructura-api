package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.CrearServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.innermodel.BuscarPorId;
import com.udemy.gestioninfraestructuraapi.application.innermodel.CrearServidor;
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

	@Autowired
	private BuscarServidorUseCase buscarServidorUseCase;
	@Autowired
	private CrearServidorUseCase crearServidorUseCase;

	@GetMapping("/")
	public ResponseEntity<List<Servidor>> buscarTodos() throws ControllerException {
		List<Servidor> servidores;

		try {
			servidores = buscarServidorUseCase.buscarTodos();
		} catch (ApplicationException e) {
			throw new ControllerException(e.getMessage(), e);
		}

		return new ResponseEntity<>(servidores, HttpStatus.OK);
	}

	@GetMapping("/buscarPorId")
	public ResponseEntity<Servidor> buscarPorId(@Valid @RequestParam BuscarPorId buscarPorId,
			BindingResult bindingResult) throws ControllerException {
		Servidor servidor;

		try {
			if (bindingResult.hasErrors()) {
				FieldError fe = (FieldError) bindingResult.getAllErrors().get(0);
				throw new ValidationException(
						fe.getField() + ": " + fe.getDefaultMessage() + " (" + fe.getRejectedValue() + ")");
			} else {
				servidor = buscarServidorUseCase.buscarServidorPorId(buscarPorId);
			}
		} catch (ApplicationException e) {
			throw new ControllerException(e.getMessage(), e);
		}

		return new ResponseEntity<>(servidor, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Servidor> crear(@Valid @RequestParam CrearServidor crearServidor, BindingResult bindingResult)
			throws ControllerException {
		Servidor servidor = null;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			if (bindingResult.hasErrors()) {
				for (ObjectError objectError : bindingResult.getAllErrors()) {
					FieldError fe = (FieldError) objectError;
					stringBuilder.append(
							fe.getField() + ": " + fe.getDefaultMessage() + " (" + fe.getRejectedValue() + ")|");
				}
				throw new ValidationException(stringBuilder.toString());
			} else {
				servidor = crearServidorUseCase.crear(crearServidor);
			}
		} catch (ApplicationException e) {
			throw new ControllerException(e.getMessage(), e);
		}

		return new ResponseEntity<>(servidor, HttpStatus.OK);
	}
}
