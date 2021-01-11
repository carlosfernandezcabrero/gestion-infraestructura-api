package com.udemy.gestioninfraestructuraapi.resource;

public class DbQuerys {
	
	private static final String FROMSERVIDORES = "FROM Servidores ";
	private static final String SELECTCOMPLETOSERVIDORES = "SELECT codigo, nombre, ip, os, fk_grupo_resolutor ";
	
	private DbQuerys() {}
	
	public static final String BUSCARTODOSSERVIDORES = SELECTCOMPLETOSERVIDORES
														  + FROMSERVIDORES;
	public static final String BUSCARPORNOMBRE = SELECTCOMPLETOSERVIDORES
													+ FROMSERVIDORES
													+ "WHERE nombre LIKE ?";
	public static final String BUSCARPORIP = SELECTCOMPLETOSERVIDORES
												+ FROMSERVIDORES
												+ "WHERE ip LIKE ?";
	public static final String BUSCARPORCODIGO = SELECTCOMPLETOSERVIDORES
												+ FROMSERVIDORES
												+ "WHERE codigo=?";
	public static final String BUSCARPOROS = SELECTCOMPLETOSERVIDORES
												+ FROMSERVIDORES
												+ "WHERE os LIKE ?";

}
