package com.udemy.gestioninfraestructuraapi.model;

public class Servidor {
	
	private Integer codigo;
    private String nombre;
    private String ip;
    private GrupoResolutor grupoResolutor;
    private String os;

    public Servidor(){}

    public Servidor(Integer codigo){
        this.setCodigo(codigo);
    }
    
    public Servidor(String nombre, String ip, GrupoResolutor grupoResolutor, String os){
        this.setNombre(nombre);
        this.setIp(ip);
        this.setGrupoResolutor(grupoResolutor);
        this.setOs(os);
    }

    public Servidor(Integer codigo, String nombre, String ip, GrupoResolutor grupoResolutor, String os){
    	this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setIp(ip);
        this.setGrupoResolutor(grupoResolutor);
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

    public GrupoResolutor getGrupoResolutor() {
        return grupoResolutor;
    }

    public void setGrupoResolutor(GrupoResolutor grupoResolutor) {
        this.grupoResolutor = grupoResolutor;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

}
