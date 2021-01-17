package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
import com.udemy.gestioninfraestructuraapi.exception.ApplicationException;
import com.udemy.gestioninfraestructuraapi.exception.ControllerException;
import com.udemy.gestioninfraestructuraapi.exception.ValidationException;
import com.udemy.gestioninfraestructuraapi.model.Servidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/servidor")
class ServidorController {

    @Autowired
    private BuscarServidorUseCase buscarServidorUseCase;
    
    @GetMapping("/")
    public ResponseEntity<List<Servidor>> buscarTodos(){
    	List<Servidor> servidores;
    	
    	try {
    		servidores = buscarServidorUseCase.buscarTodos();
    	} catch(ApplicationException e) {
    		throw new ControllerException(e.getMessage(), e);
    	}
    	
        return new ResponseEntity<>(servidores, HttpStatus.OK);
    }
    
    @GetMapping("/buscarPorId")
    public ResponseEntity<Servidor> buscarPorId(@Valid @RequestParam BuscarServidorUseCase.BuscarPorId buscarPorId, BindingResult bindingResult){
    	Servidor servidor;
    	
    	try {
    		if(bindingResult.hasErrors()){
				FieldError fe = (FieldError) bindingResult.getAllErrors().get(0);
				throw new ValidationException(fe.getField() + ": " + fe.getDefaultMessage() + " (" + fe.getRejectedValue() + ")");
			}else{
				servidor = buscarServidorUseCase.buscarServidorPorId(buscarPorId);
			}
    	} catch(ApplicationException e) {
    		throw new ControllerException(e.getMessage(), e);
    	}
    	
    	return new ResponseEntity<>(servidor, HttpStatus.OK);
    }
}
