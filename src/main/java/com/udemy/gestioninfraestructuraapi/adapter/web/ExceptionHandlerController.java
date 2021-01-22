package com.udemy.gestioninfraestructuraapi.adapter.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.udemy.gestioninfraestructuraapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;

@RestControllerAdvice
class ExceptionHandlerController {

	/***
	 * Metodo que captura las excepciones de tipo ControllerException
	 * @param ex
	 * @return ResponseEntity de String y HttpStatus INTERNAL_SERVER_ERROR
	 */
	@ExceptionHandler(value = ControllerException.class)
    public ResponseEntity<String> controllerException(ControllerException ex) {
		if (ex.getCause() == null){
			return ResponseEntity.badRequest().body(ex.getMessage());
		} else {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
    }

	/***
	 * Metodo que captura las excepciones de tipo ValidationException
	 * @param e
	 * @return ResponseEntity de List de String y HttpStatus BAD_REQUEST
	 */
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<List<String>> validationException(ValidationException e){
		List<String> errorResponses = new ArrayList<>(Arrays.asList(e.getMessage().split("\\|")));
    	return ResponseEntity.badRequest().body(errorResponses);
	}

	/***
	 * Metodo que captura las excepciones de tipo NotFoundException
	 * @param exception
	 * @return ResponseEntity de String y HttpStatus NO_CONTENT
	 */
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<String> notFoundException(NotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
}
