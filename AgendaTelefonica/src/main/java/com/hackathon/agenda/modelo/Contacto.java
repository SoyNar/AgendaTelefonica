package com.hackathon.agenda.modelo;

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    //constructor
    public Contacto(String nombre, String apellido, String telefono) {
        if(esDatoValido(nombre)!=true){
            throw new IllegalArgumentException("El Nomrbe no es válido");
        }
        if(esDatoValido(apellido)!=true){
            throw new IllegalArgumentException("El Apellido no es válido");

        }
        if (esTelefonoValido(telefono)!=true){
            throw new IllegalArgumentException("El Telefono no es válido");

        }else {
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
        }

    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(esDatoValido(nombre)!=true){
            throw new IllegalArgumentException("El Nomrbe no es válido");
        }else {
            this.nombre = nombre;
        }

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if(esDatoValido(apellido)!=true){
            throw new IllegalArgumentException("El Apellido no es válido");

        }else{
            this.apellido = apellido;

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
        String reglaTel= "(\\+\\d{1,2} )?\\d{9}";

        if(telefono ==null){
            return false;
        }
        return telefono.matches(reglaTel);
    }





}

