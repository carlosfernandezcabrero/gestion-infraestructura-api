INSERT INTO GruposResolutores VALUES ('Storage', 'Departamento encargado de el almacenaje de datos');
INSERT INTO GruposResolutores VALUES ('Comunicaciones', 'Departamento de la gestion de las comunicaciones');
INSERT INTO GruposResolutores VALUES ('Gestion de cambios', 'Departamento encargado de la administracion de los cambios en aplicativos');

INSERT INTO Servidores VALUES (null, 'splunk-server', '192.168.1.1', 'Windows NT', 'Storage');
INSERT INTO Servidores VALUES (null, 'angular-server', '192.168.1.2', 'RedHat Santiago', 'Storage');
INSERT INTO Servidores VALUES (null, 'python-server', '192.168.1.3', 'RedHat Santiago', 'Comunicaciones');
INSERT INTO Servidores VALUES (null, 'java-server', '192.168.1.14', 'Solaris', 'Gestion de cambios');

COMMIT;