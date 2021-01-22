package com.udemy.gestioninfraestructuraapi.resource;

public class DbQuerys {

	private DbQuerys() {}

	/**
	 * Servidor
	 */
	private static final String FROM_SERVIDORES = "FROM Servidores ";
	private static final String SELECT_COMPLETO_SERVIDORES = "SELECT codigo, nombre, ip, os, fk_grupo_resolutor ";

	public static final String BUSCAR_TODOS_SERVIDORES = 	SELECT_COMPLETO_SERVIDORES
															+ FROM_SERVIDORES;
	public static final String BUSCAR_POR_CODIGO = 			SELECT_COMPLETO_SERVIDORES
															+ FROM_SERVIDORES
															+ "WHERE codigo=?";
	public static final String CREAR_SERVIDOR =				"INSERT INTO Servidores VALUES (null, ?, ?, ?, ?)";

	/**
	 * GrupoResolutor
	 */
	private static final String SELECT_COMPLETO_GRUPORESOLUTOR = "SELECT nombre, descripcion ";
	private static final String FROM_GRUPORESOLUTOR = "FROM GruposResolutores ";

	public static final String BUSCAR_TODOS_GRUPORESOLUTOR = 	SELECT_COMPLETO_GRUPORESOLUTOR
																+ FROM_GRUPORESOLUTOR;
	public static final String BUSCAR_POR_NOMBRE = 				SELECT_COMPLETO_GRUPORESOLUTOR
																+ FROM_GRUPORESOLUTOR
																+ "WHERE nombre=?";

}
