package com.hackathon.agenda.modelo;

public class Contacto {
    private String nombre;
    private String apellido;
    private String numero;

    //constructor
    public Contacto(String apellido, String numero, String nombre) {
        this.apellido = apellido;
        this.numero = numero;
        this.nombre = nombre;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
