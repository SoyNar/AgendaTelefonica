package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VistaPrueba;

/**
 * Controlador de la agenda telefonica.
 *
 * <p>Crea su propio modelo, conecta los botones y la tabla de la vista,
 * y traduce las acciones del usuario en llamadas a {@link Agenda}.
 * Las excepciones de validacion se muestran en el panel de estado.</p>
 */
public class AgendaControlador {

    private final Agenda agenda;
    private final VistaPrueba vista;

    /**
     * @param vista ventana que muestra los datos y recibe las acciones del usuario
     */
    public AgendaControlador(VistaPrueba vista) {
        this.agenda = new Agenda();
        this.vista = vista;
        conectarEventos();
        refrescarTabla();
    }

    /** Asigna cada boton y la tabla a su accion. */
    private void conectarEventos() {
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnEditar().addActionListener(e -> editar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnLimpiar().addActionListener(e -> vista.limpiarFormulario());

        // Al seleccionar una fila, el formulario se llena con ese contacto
        vista.getTablaContactos().getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            Contacto c = contactoSeleccionado();
            if (c != null) {
                vista.rellenarFormulario(c.getNombre(), c.getApellido(), c.getTelefono());
            }
        });
    }

    /** Crea un contacto con los datos del formulario y lo añade. */
    private void guardar() {
        try {
            Contacto nuevo = new Contacto(
                    textoDeCampo(vista.getTxtNombre().getText()),
                    textoDeCampo(vista.getTxtApellido().getText()),
                    textoDeCampo(vista.getTxtTelefono().getText()));
            agenda.anadirContacto(nuevo);
            refrescarTabla();
            vista.limpiarFormulario();
            vista.mostrarMensajeExito("Contacto guardado correctamente.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            vista.mostrarMensajeError(e.getMessage());
        }
    }

    /** Cambia el telefono del contacto seleccionado en la tabla. */
    private void editar() {
        Contacto seleccionado = contactoSeleccionado();
        if (seleccionado == null) {
            vista.mostrarMensajeError("Selecciona un contacto de la tabla para editarlo.");
            return;
        }
        try {
            agenda.modificarTelefono(
                    seleccionado.getNombre(),
                    seleccionado.getApellido(),
                    textoDeCampo(vista.getTxtTelefono().getText()));
            refrescarTabla();
            vista.mostrarMensajeExito("Teléfono actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            vista.mostrarMensajeError(e.getMessage());
        }
    }

    /** Elimina el contacto seleccionado en la tabla. */
    private void eliminar() {
        Contacto seleccionado = contactoSeleccionado();
        if (seleccionado == null) {
            vista.mostrarMensajeError("Selecciona un contacto de la tabla para eliminarlo.");
            return;
        }
        agenda.eliminarContacto(seleccionado);
        refrescarTabla();
        vista.limpiarFormulario();
        vista.mostrarMensajeExito("Contacto eliminado.");
    }

    /** Muestra en el panel de estado el telefono del nombre y apellido escritos. */
    private void buscar() {
        String nombre = textoDeCampo(vista.getTxtNombre().getText());
        String apellido = textoDeCampo(vista.getTxtApellido().getText());
        if (nombre.isEmpty() || apellido.isEmpty()) {
            vista.mostrarMensajeError("Escribe nombre y apellido para buscar.");
            return;
        }
        try {
            String telefono = agenda.buscarContacto(nombre, apellido);
            vista.mostrarMensajeExito("Teléfono de " + nombre + " " + apellido + ": " + telefono);
        } catch (IllegalArgumentException e) {
            vista.mostrarMensajeError(e.getMessage());
        }
    }

    /** Devuelve el contacto de la fila seleccionada, o null si no hay ninguna. */
    private Contacto contactoSeleccionado() {
        int fila = vista.getFilaSeleccionada();
        if (fila < 0) {
            return null;
        }
        // La tabla se llena con listarContactos(), asi que el indice coincide
        return agenda.listarContactos().get(fila);
    }

    /** Vuelve a llenar la tabla con la lista ordenada del modelo. */
    private void refrescarTabla() {
        vista.cargarTabla(agenda.listarContactos());
    }

    /** Quita espacios al inicio y al final; null se convierte en cadena vacia. */
    private static String textoDeCampo(String texto) {
        return texto == null ? "" : texto.trim();
    }
}