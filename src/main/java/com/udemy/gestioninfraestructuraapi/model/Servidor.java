package com.udemy.gestioninfraestructuraapi.model;

public class Servidor {
	
	private Integer id;
    private String nombre;
    private String ip;
    private String os;

    public Servidor(){}

    public Servidor(Integer id){
        this.setId(id);
    }
    
    public Servidor(String nombre, String ip, String os){
        this.setNombre(nombre);
        this.setIp(ip);
        this.setOs(os);
    }

    public Servidor(Integer codigo, String nombre, String ip, String os){
    	this.setId(codigo);
        this.setNombre(nombre);
        this.setIp(ip);
        this.setOs(os);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
