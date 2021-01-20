package com.udemy.gestioninfraestructuraapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServidorTest {

    private Servidor servidor;

    @BeforeEach
    void setUp() {
        servidor = new Servidor();
    }

    @Test
    void nombre() {
        String nombre = "splunk-server";
        servidor.setNombre(nombre);
        assertEquals(nombre, servidor.getNombre());
    }

    @Test
    void ip() {
        String ip = "192.168.1.10";
        servidor.setIp(ip);
        assertEquals(ip, servidor.getIp());
    }

    @Test
    void os() {
        String os = "Windows NT";
        servidor.setOs(os);
        assertEquals(os, servidor.getOs());
    }

    @Test
    void id() {
        long id = 1;
        servidor.setCodigo(id);
        assertEquals(id, servidor.getCodigo());
    }
}