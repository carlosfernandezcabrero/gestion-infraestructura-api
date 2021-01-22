package com.udemy.gestioninfraestructuraapi.adapter.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import com.udemy.gestioninfraestructuraapi.resource.ErrorResponse;

@RestControllerAdvice
class ExceptionHandlerController {

	/***
	 * Metodo que captura las excepciones de tipo ControllerException
	 * @param ex
	 * @return ResponseEntity de ErrorResponse y HttpStatus INTERNAL_SERVER_ERROR
	 */
	@ExceptionHandler(value = ControllerException.class)
    public ResponseEntity<ErrorResponse> controllerException(ControllerException ex) {
    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	/***
	 * Metodo que captura las excepciones de tipo ValidationException
	 * @param e
	 * @return ResponseEntity de List de ErrorResponse y HttpStatus BAD_REQUEST
	 */
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<List<ErrorResponse>> validationException(ValidationException e){
    	List<ErrorResponse> errorResponses = new ArrayList<>();

    	for(String i : e.getMessage().split("\\|")){
    		errorResponses.add(new ErrorResponse(i));
		}

    	return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);
	}
	
}
