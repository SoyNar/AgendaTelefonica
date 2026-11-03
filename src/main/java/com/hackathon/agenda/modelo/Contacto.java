package com.hackathon.agenda.modelo;

import java.util.Objects;

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    //constructor
    public Contacto(String nombre, String apellido, String telefono) {
        if(esDatoValido(nombre)!=true){
            throw new IllegalArgumentException("El Nombre no es válido");
        }
        if(esDatoValido(apellido)!=true){
            throw new IllegalArgumentException("El Apellido no es válido");

        }
        if (esTelefonoValido(telefono)!=true){
            throw new IllegalArgumentException("El Telefono no es válido");

        }
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(esDatoValido(nombre)!=true){
            throw new IllegalArgumentException("El Nombre no es válido");
        }else {
            this.nombre = nombre.trim();
        }

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if(esDatoValido(apellido)!=true){
            throw new IllegalArgumentException("El Apellido no es válido");

        }else{
            this.apellido = apellido.trim();

        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(esTelefonoValido(telefono)==false){
            throw new IllegalArgumentException("El télefono no es válido");
        }else {
            this.telefono = telefono;
        }
    }

    //metodo para validar si nombre y apellido son valios
    public static boolean esDatoValido(String dato){
        if(dato==null){
           return false;
        }else if(dato.isBlank()==true){
            return false;
        } else{
            return true;
        }
        }
    //metodo validar si telefono es valido
    public static boolean esTelefonoValido(String telefono){
        String reglaTel = "(\\+\\d{1,2} ?)?(\\d{9}|\\d{3} \\d{3} \\d{3})";


        if(telefono ==null){
            return false;
        }
        return telefono.matches(reglaTel);
    }

    //sobreescritura de metodos para validar si un contacto es igual
    @Override
    public boolean equals(Object contacto){ //antes del punto del metodo nombre, apellido, telefono vinenen del constructor
        if(!(contacto instanceof Contacto)){
            return false;
        }
        Contacto c = (Contacto)contacto; //parametro pasado al metodo

        if(c==null){
            return false;
        }
        boolean validacionNombre= nombre.equalsIgnoreCase(c.nombre);
        boolean validacionApellido =apellido.equalsIgnoreCase(c.apellido);
        if(validacionNombre&&validacionApellido==true){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

}
