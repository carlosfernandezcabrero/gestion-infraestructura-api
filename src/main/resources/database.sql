DROP TABLE IF EXISTS Servidores;
DROP TABLE IF EXISTS GruposResolutores;

CREATE TABLE GruposResolutores(
	nombre varchar(30),
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

INSERT INTO GruposResolutores VALUES ('Storage');
INSERT INTO GruposResolutores VALUES ('Comunicaciones');
INSERT INTO GruposResolutores VALUES ('Gestion de cambios');

INSERT INTO Servidores VALUES (null, 'splunk-server', '192.168.1.1', 'Windows NT', 'Storage');
INSERT INTO Servidores VALUES (null, 'angular-server', '192.168.1.2', 'RedHat Santiago', 'Storage');
INSERT INTO Servidores VALUES (null, 'python-server', '192.168.1.3', 'RedHat Santiago', 'Comunicaciones');
INSERT INTO Servidores VALUES (null, 'java-server', '192.168.1.14', 'Solaris', 'Gestion de cambios');

COMMIT;
