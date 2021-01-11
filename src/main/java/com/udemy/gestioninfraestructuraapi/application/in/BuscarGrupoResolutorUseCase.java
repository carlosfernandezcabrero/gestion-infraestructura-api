package com.udemy.gestioninfraestructuraapi.application.in;

import java.util.List;

import com.udemy.gestioninfraestructuraapi.model.GrupoResolutor;

public interface BuscarGrupoResolutorUseCase {
	
	GrupoResolutor buscarPorId(String nombre);
	List<GrupoResolutor> buscarPorNombre(String nombre);

}
