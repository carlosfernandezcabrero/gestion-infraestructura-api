package com.udemy.gestioninfraestructuraapi.application.innermodel;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class BuscarPorId {

    @NotBlank
    private String id;

    public BuscarPorId(String id){
        this.id = id;
    }
    
    public String getId() {
    	return id;
    }
}
