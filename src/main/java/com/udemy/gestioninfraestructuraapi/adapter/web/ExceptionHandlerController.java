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
	
	@ExceptionHandler(value = ControllerException.class)
    public ResponseEntity<ErrorResponse> controllerException(ControllerException ex) {
    	ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

    	if(ex.getCause() != null) {
    		ex.printStackTrace();
    		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    	} else {
    		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    	}
    }

    @ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<List<ErrorResponse>> validationException(ValidationException e){
    	List<ErrorResponse> errorResponses = new ArrayList<>();

    	for(String i : e.getMessage().split("\\|")){
    		errorResponses.add(new ErrorResponse(i));
		}

    	return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);
	}
	
}
