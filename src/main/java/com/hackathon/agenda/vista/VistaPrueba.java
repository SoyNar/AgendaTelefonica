package com.hackathon.agenda.vista;

import com.hackathon.agenda.modelo.Contacto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaPrueba extends JFrame {

    private final PanelEncabezado encabezado = new PanelEncabezado();
    private final PanelFormulario formulario = new PanelFormulario();
    private final PanelTabla tabla = new PanelTabla();
    private final JLabel lblEstado = new JLabel(" ", SwingConstants.CENTER);

    public VistaPrueba() {
        setTitle("Agenda Telefónica - Hackathon Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(Math.min(1080, pantalla.width - 80), Math.min(740, pantalla.height - 100));
        setMinimumSize(new Dimension(880, 600));
        setLocationRelativeTo(null);

        construirInterfaz();
    }

    private void construirInterfaz() {
        lblEstado.setFont(lblEstado.getFont().deriveFont(Font.BOLD, 13f));

        PanelTarjeta barraEstado = new PanelTarjeta(new BorderLayout(), 999, new Color(20, 12, 44, 190));
        barraEstado.setBorder(BorderFactory.createEmptyBorder(9, 18, 9, 18));
        barraEstado.add(lblEstado, BorderLayout.CENTER);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setOpaque(false);
        sur.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
        sur.add(barraEstado, BorderLayout.CENTER);

        JPanel superior = new JPanel(new BorderLayout());
        superior.setOpaque(false);
        superior.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));
        superior.add(encabezado, BorderLayout.NORTH);
        superior.add(formulario, BorderLayout.CENTER);

        PanelFondo fondo = new PanelFondo();
        fondo.setBorder(BorderFactory.createEmptyBorder(0, 28, 20, 28));
        fondo.add(superior, BorderLayout.NORTH);
        fondo.add(tabla, BorderLayout.CENTER);
        fondo.add(sur, BorderLayout.SOUTH);
        setContentPane(fondo);
    }

    // ---- Métodos que usa el controlador (sin cambios de firma) ----

    public JTextField getTxtNombre()   { return formulario.getNombre(); }
    public JTextField getTxtApellido() { return formulario.getApellido(); }
    public JTextField getTxtTelefono() { return formulario.getTelefono(); }

    public JButton getBtnGuardar()  { return formulario.getBtnGuardar(); }
    public JButton getBtnEditar()   { return formulario.getBtnEditar(); }
    public JButton getBtnEliminar() { return formulario.getBtnEliminar(); }
    public JButton getBtnBuscar()   { return formulario.getBtnBuscar(); }
    public JButton getBtnLimpiar()  { return formulario.getBtnLimpiar(); }

    public JTable getTablaContactos()         { return tabla.getTabla(); }
    public DefaultTableModel getModeloTabla() { return tabla.getModelo(); }
    public int getFilaSeleccionada()          { return tabla.getFilaSeleccionada(); }

    public void cargarTabla(List<Contacto> contactos) {
        tabla.cargar(contactos);
        encabezado.actualizarConteo(contactos.size());
    }

    public void rellenarFormulario(String nombre, String apellido, String telefono) {
        formulario.rellenar(nombre, apellido, telefono);
    }

    public void limpiarFormulario() {
        formulario.limpiar();
        tabla.limpiarSeleccion();
    }

    public void mostrarMensajeError(String mensaje) {
        lblEstado.setForeground(new Color(248, 113, 113));
        lblEstado.setText(mensaje);
    }

    public void mostrarMensajeExito(String mensaje) {
        lblEstado.setForeground(new Color(74, 222, 128));
        lblEstado.setText(mensaje);
    }
}