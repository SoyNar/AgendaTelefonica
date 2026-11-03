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
    public void anadirContacto(Contacto c) {
        if (c == null) {
            throw new IllegalArgumentException("El campo no puede estar vacío");
        }
        if (agendaLlena()) {
            throw new IllegalStateException("La agenda está llena, no puede agregar más contactos");
        }

        String claveNueva = generarClave(c);

        for (int i = 0; i < contactos.size(); i++) {
            Contacto existente = contactos.get(i);
            String claveExistente = generarClave(existente);

            if (claveExistente.equals(claveNueva)) {
                throw new IllegalArgumentException(
                        "El contacto ya existe en la posición " + (i + 1) + ": "
                                + existente.getNombre() + " " + existente.getApellido());
            }
        }

        contactos.add(c);
    }

    // PEGAR AQUÍ EL NUEVO MÉTODO:
    public boolean existeContacto(Contacto c) {
        if (c == null) {
            return false;
        }
        String clave = generarClave(c);
        for (Contacto existente : contactos) {
            if (generarClave(existente).equals(clave)) {
                return true;
            }
        }
        return false;
    }

    // 4. Si pasa las validaciones, lo añade a la lista
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

        String claveBuscada = (nombre.trim() + apellido.trim()).toLowerCase();

        for (Contacto existente : contactos) {
            if (generarClave(existente).equals(claveBuscada)) {
                return existente.getTelefono();
            }
        }

        return null;
    }

    public boolean eliminarContacto(Contacto c) {
        if (c == null) {
            throw new IllegalArgumentException("Contacto no nulo");
        }

        String claveAEliminar = generarClave(c);

        for (int i = 0; i < contactos.size(); i++) {
            if (generarClave(contactos.get(i)).equals(claveAEliminar)) {
                contactos.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        if (nombre == null || nombre.isBlank() || apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("Campos obligatorios");
        }

        if (!Contacto.esTelefonoValido(nuevoTelefono)) {
            throw new IllegalArgumentException("Formato inválido");
        }

        String claveBuscada = (nombre.trim() + apellido.trim()).toLowerCase();

        for (Contacto existente : contactos) {
            if (generarClave(existente).equals(claveBuscada)) {
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

    // Normaliza clave de comparación
    // Método auxiliar privado para no repetir la lógica de concatenar y pasar a
    // minúscula
    private String generarClave(Contacto c) {
        return (c.getNombre().trim() + c.getApellido().trim()).toLowerCase();
    }
}
