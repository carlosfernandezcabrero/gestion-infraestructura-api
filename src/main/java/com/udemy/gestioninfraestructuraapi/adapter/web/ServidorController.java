package com.udemy.gestioninfraestructuraapi.adapter.web;

import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorIp;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorNombre;
import com.udemy.gestioninfraestructuraapi.application.in.BuscarServidorUseCase.BuscadorServidorOs;
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

import java.util.List;

import javax.validation.Valid;

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
    
    @GetMapping("/buscarPorNombre")
    public ResponseEntity<List<Servidor>> buscarPorNombre(@Valid BuscadorServidorNombre nombre, BindingResult bindingResult){
    	List<Servidor> servidores;
    	FieldError fe;
    	
    	try {
    		if(bindingResult.hasErrors()) {
    			fe = (FieldError) bindingResult.getAllErrors().get(0);
    			throw new ValidationException(fe.getField() + ": " + fe.getDefaultMessage() + " (" + fe.getRejectedValue() + ")");
    		} else {
    			servidores = buscarServidorUseCase.buscarServidorPorNombre(nombre);
    		}
    	} catch(ApplicationException e) {
    		throw new ControllerException(e.getMessage(), e);
    	}
    	
        return new ResponseEntity<>(servidores, HttpStatus.OK);
    }
    
    @GetMapping("/buscarPorIp")
    public ResponseEntity<List<Servidor>> buscarPorIp(@Valid BuscadorServidorIp ip, BindingResult bindingResult){
    	List<Servidor> servidores;
    	FieldError fe;
    	
    	try {
    		if(bindingResult.hasErrors()) {
    			fe = (FieldError) bindingResult.getAllErrors().get(0);
    			throw new ValidationException(fe.getField() + ": " + fe.getDefaultMessage() + " (" + fe.getRejectedValue() + ")");
    		} else {
    			servidores = buscarServidorUseCase.buscarServidorPorIp(ip);
    		}
    	} catch(ApplicationException e) {
    		throw new ControllerException(e.getMessage(), e);
    	}
    	
        return new ResponseEntity<>(servidores, HttpStatus.OK);
    }
    
    @GetMapping("/buscarPorId")
    public ResponseEntity<Servidor> buscarPorId(@RequestParam String id){
    	Servidor servidor;
    	
    	try {
    		servidor = buscarServidorUseCase.buscarServidorPorId(id);
    	} catch(ApplicationException e) {
    		throw new ControllerException(e.getMessage(), e);
    	}
    	
    	return new ResponseEntity<>(servidor, HttpStatus.OK);
    }
    
    @GetMapping("/buscarPorOs")
    public ResponseEntity<List<Servidor>> buscarPorOs(@Valid BuscadorServidorOs os, BindingResult bindingResult){
    	List<Servidor> servidores;
    	FieldError fe;
    	
    	try {
    		if(bindingResult.hasErrors()) {
    			fe = (FieldError) bindingResult.getAllErrors().get(0);
    			throw new ValidationException(fe.getField() + ": " + fe.getDefaultMessage() + " (" + fe.getRejectedValue() + ")");
    		} else {
    			servidores = buscarServidorUseCase.buscarServidorPorOs(os);
    		}
    	} catch(ApplicationException e) {
    		throw new ControllerException(e.getMessage(), e);
    	}
    	
        return new ResponseEntity<>(servidores, HttpStatus.OK);
    }

}
