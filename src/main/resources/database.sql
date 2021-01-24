DROP TABLE IF EXISTS Servidores;
DROP TABLE IF EXISTS GruposResolutores;

CREATE TABLE GruposResolutores(
	nombre varchar(30),
	descripcion varchar(200),
	CONSTRAINT PK_GRUPOSRESOLUTORES PRIMARY KEY (nombre)
);

CREATE TABLE Servidores(
	codigo int AUTO_INCREMENT,
	nombre varchar(30),
	ip varchar(20),
	os varchar(50),
	fk_grupo_resolutor varchar(30),
	CONSTRAINT PK_SERVIDORES PRIMARY KEY (codigo),
	CONSTRAINT FK_GRUPOSRESOLUTORES FOREIGN KEY (fk_grupo_resolutor) REFERENCES GruposResolutores(nombre)
);

COMMIT;
