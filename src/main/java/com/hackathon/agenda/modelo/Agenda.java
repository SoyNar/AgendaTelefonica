package com.hackathon.agenda.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {

    // Tamaño máximo por defecto: 10
    private static final int CAPACIDAD_DEFECTO = 10;

    private final int capacidadMaxima;
    private final List<Contacto> contactos;

    // Constructor por defecto (máximo 10 contactos)
    public Agenda() {
        this(CAPACIDAD_DEFECTO);
    }

    // Constructor configurable al momento de iniciar agenda
    public Agenda(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("Capacidad mayor a 0");
        }
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    // Añadir contacto a la agenda
    // Añadir contacto a la agenda
    public void añadirContacto(Contacto c) {
        if (c == null) {
            throw new IllegalArgumentException("El campo no puede estar vacío");
        }
        if (agendaLlena()) {
            throw new IllegalStateException("La agenda está llena, no puede agregar más contactos");
        }
        if(existeContacto(c)){
            throw new IllegalArgumentException("El contacto ya existe en la agenda");
        }

        contactos.add(c);
    }

    public boolean existeContacto(Contacto c) {
        if(c == null){
            return false;
        }
        return contactos.contains(c);
    }

    // Si pasa las validaciones, lo añade a la lista
    public List<Contacto> listarContactos() {
        List<Contacto> ordenados = new ArrayList<>(contactos);
        ordenados.sort((c1, c2) -> {
            int compNombre = c1.getNombre().compareToIgnoreCase(c2.getNombre());
            if (compNombre != 0) {
                return compNombre;
            }
            return c1.getApellido().compareToIgnoreCase(c2.getApellido());
        });
        return Collections.unmodifiableList(ordenados);
    }

    public String buscarContacto(String nombre, String apellido) {
        if (nombre == null || nombre.isBlank() || apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("Campos obligatorios");
        }

        for (Contacto existente : contactos) {
            if (existente.getNombre().equalsIgnoreCase(nombre.trim()) && existente.getApellido().equalsIgnoreCase(apellido.trim())) {
                return existente.getTelefono();
            }
        }

        return null;
    }

    public boolean eliminarContacto(Contacto c) {
        if (c == null) {
            throw new IllegalArgumentException("Contacto no nulo");
        }

        return contactos.remove(c);
    }

    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        if (nombre == null || nombre.isBlank() || apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("Campos obligatorios");
        }

        if (!Contacto.esTelefonoValido(nuevoTelefono)) {
            throw new IllegalArgumentException("Formato inválido");
        }

        for (Contacto existente : contactos) {
            if (existente.getNombre().equalsIgnoreCase(nombre.trim()) && existente.getApellido().equalsIgnoreCase(apellido.trim())) {
                existente.setTelefono(nuevoTelefono);
                return true;
            }
        }

        return false;
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public int getCantidadContactos() {
        return contactos.size();
    }
}
