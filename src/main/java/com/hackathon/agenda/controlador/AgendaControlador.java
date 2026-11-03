package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.ComponentesGraficos;
import com.hackathon.agenda.vista.VentanaPrincipal;
import com.hackathon.agenda.vista.VistaPrueba;
import java.awt.Color;

public class AgendaControlador {

    private final Agenda agenda;
    private final VentanaPrincipal vista;

    // Colores base para los estados visuales en la interfaz gráfica segun
    private static final Color COLOR_EXITO = new Color(34, 139, 34);
    private static final Color COLOR_ERROR = Color.RED;

    public AgendaControlador(Agenda agenda, VentanaPrincipal vista) {
        this.agenda = agenda;
        this.vista = vista;
        conectarEventos();
        refrescarTabla();
    }

    private void conectarEventos() {
        vista.getBtnAnadir().addActionListener(e -> anadir());
        vista.getBtnModificar().addActionListener(e -> modificar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnLimpiar().addActionListener(e -> {
            vista.limpiarCampos();
            ComponentesGraficos.mostrarMensaje(vista.lblEstado, "Formulario limpio.", COLOR_EXITO);
            vista.setCapacidad(agenda.espaciosLibres(), agenda.getCapacidadMaxima());
        });

        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int fila = vista.getFilaSeleccionada();
            if (fila >= 0) {
                vista.setCampos(
                        vista.getValorTabla(fila, 0),
                        vista.getValorTabla(fila, 1),
                        vista.getValorTabla(fila, 2)
                );
            }
        });
    }

    private void anadir() {
        try {
            Contacto c = new Contacto(vista.getNombre(), vista.getApellido(), vista.getTelefono());
            agenda.anadirContacto(c); // Sincronizado con 'anadirContacto' en tu Agenda.java
            refrescarTabla();
            vista.limpiarCampos();
            ComponentesGraficos.mostrarMensaje(
                    vista.lblEstado,
                    "Contacto añadido: " + c.getNombre() + " " + c.getApellido(),
                    COLOR_EXITO
            );
            vista.setCapacidad(agenda.espaciosLibres(), agenda.getCapacidadMaxima());
        } catch (IllegalArgumentException | IllegalStateException ex) {
            ComponentesGraficos.mostrarMensaje(vista.lblEstado, ex.getMessage(), COLOR_ERROR);
        }
    }

    private void modificar() {
        try {
            boolean exito = agenda.modificarTelefono(vista.getNombre(), vista.getApellido(), vista.getTelefono());
            if (exito) {
                refrescarTabla();
                ComponentesGraficos.mostrarMensaje(vista.lblEstado, "Teléfono actualizado correctamente.", COLOR_EXITO);
            } else {
                ComponentesGraficos.mostrarMensaje(vista.lblEstado, "No se encontró el contacto para modificar.", COLOR_ERROR);
            }
            vista.setCapacidad(agenda.espaciosLibres(), agenda.getCapacidadMaxima());
        } catch (IllegalArgumentException ex) {
            ComponentesGraficos.mostrarMensaje(vista.lblEstado, ex.getMessage(), COLOR_ERROR);
        }
    }

    private void eliminar() {
        try {
            String telefono = agenda.buscarContacto(vista.getNombre(), vista.getApellido());
            if (telefono == null) {
                ComponentesGraficos.mostrarMensaje(vista.lblEstado, "No existe ese contacto en la agenda.", COLOR_ERROR);
                return;
            }

            Contacto temporal = new Contacto(vista.getNombre(), vista.getApellido(), telefono);
            boolean exito = agenda.eliminarContacto(temporal);

            if (exito) {
                refrescarTabla();
                vista.limpiarCampos();
                ComponentesGraficos.mostrarMensaje(vista.lblEstado, "Contacto eliminado.", COLOR_EXITO);
            } else {
                ComponentesGraficos.mostrarMensaje(vista.lblEstado, "No se pudo eliminar el contacto.", COLOR_ERROR);
            }
            vista.setCapacidad(agenda.espaciosLibres(), agenda.getCapacidadMaxima());
        } catch (IllegalArgumentException ex) {
            ComponentesGraficos.mostrarMensaje(vista.lblEstado, ex.getMessage(), COLOR_ERROR);
        }
    }

    private void buscar() {
        try {
            String telefono = agenda.buscarContacto(vista.getNombre(), vista.getApellido());
            if (telefono == null) {
                ComponentesGraficos.mostrarMensaje(vista.lblEstado, "No se encontró el contacto (revise nombre y apellido).", COLOR_ERROR);
            } else {
                vista.setTelefono(telefono);
                ComponentesGraficos.mostrarMensaje(vista.lblEstado, "Contacto encontrado. Teléfono: " + telefono, COLOR_EXITO);
            }
            vista.setCapacidad(agenda.espaciosLibres(), agenda.getCapacidadMaxima());
        } catch (IllegalArgumentException ex) {
            ComponentesGraficos.mostrarMensaje(vista.lblEstado, ex.getMessage(), COLOR_ERROR);
        }
    }

    private void refrescarTabla() {
        vista.limpiarTabla();
        for (Contacto c : agenda.listarContactos()) {
            vista.agregarFila(c.getNombre(), c.getApellido(), c.getTelefono());
        }
        vista.setCapacidad(agenda.espaciosLibres(), agenda.getCapacidadMaxima());
    }
}
