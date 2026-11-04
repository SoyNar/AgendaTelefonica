package com.hackathon.agenda.vista;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

public class PanelFormulario extends PanelTarjeta {

    private final JTextField nombre   = campo("Ej: Carlos");
    private final JTextField apellido = campo("Ej: García");
    private final JTextField telefono = campo("Ej: 600 000 000");

    private final JButton btnGuardar  = boton("Guardar", "primary", "icons/save.svg");
    private final JButton btnEditar   = boton("Editar", null, "icons/edit.svg");
    private final JButton btnEliminar = boton("Eliminar", "danger", "icons/trash.svg");
    private final JButton btnBuscar   = boton("Buscar", null, "icons/search.svg");
    private final JButton btnLimpiar  = boton("Limpiar", null, "icons/clear.svg");

    public PanelFormulario() {
        super(new BorderLayout(0, 18), 22, new Color(30, 20, 61, 170));
        setBorder(BorderFactory.createEmptyBorder(20, 22, 20, 22));

        JPanel campos = new JPanel(new GridLayout(1, 3, 16, 0));
        //estilo botones
        estilo(btnEditar,  "#059669", "#ffffff");
        estilo(btnBuscar,  "#d97706", "#ffffff");
        estilo(btnLimpiar, "#475569", "#ffffff");
        estilo(btnGuardar, "#3b82f6", "#ffffff");
        estilo(btnEliminar, "#ef4444", "#ffffff");

        campos.setOpaque(false);
        campos.add(conEtiqueta("NOMBRE", nombre));
        campos.add(conEtiqueta("APELLIDO", apellido));
        campos.add(conEtiqueta("TELÉFONO", telefono));

        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        izquierda.setOpaque(false);
        izquierda.add(btnGuardar);
        izquierda.add(btnEditar);
        izquierda.add(btnBuscar);
        izquierda.add(btnLimpiar);

        JPanel acciones = new JPanel(new BorderLayout());
        acciones.setOpaque(false);
        acciones.add(izquierda, BorderLayout.CENTER);
        acciones.add(btnEliminar, BorderLayout.EAST);

        add(campos, BorderLayout.NORTH);
        add(acciones, BorderLayout.CENTER);
    }

    private static JTextField campo(String pista) {
        JTextField f = new JTextField(12);
        f.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, pista);
        f.setMargin(new Insets(8, 12, 8, 12));
        return f;
    }

    private static JPanel conEtiqueta(String texto, JTextField campo) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(etiqueta.getFont().deriveFont(Font.BOLD, 11f));
        etiqueta.setForeground(new Color(196, 181, 253));

        JPanel p = new JPanel(new BorderLayout(0, 6));
        p.setOpaque(false);
        p.add(etiqueta, BorderLayout.NORTH);
        p.add(campo, BorderLayout.CENTER);
        return p;
    }

    private static JButton boton(String texto, String estilo, String rutaIcono) {
        JButton b = new JButton(texto);
        if (rutaIcono != null) {
            FlatSVGIcon icono = new FlatSVGIcon(rutaIcono, 16, 16);
            icono.setColorFilter(new FlatSVGIcon.ColorFilter(c -> Color.WHITE));
            b.setIcon(icono);
        }
        if (estilo != null) {
            b.putClientProperty(FlatClientProperties.STYLE_CLASS, estilo);
        }
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    public JTextField getNombre()   { return nombre; }
    public JTextField getApellido() { return apellido; }
    public JTextField getTelefono() { return telefono; }

    public JButton getBtnGuardar()  { return btnGuardar; }
    public JButton getBtnEditar()   { return btnEditar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnBuscar()   { return btnBuscar; }
    public JButton getBtnLimpiar()  { return btnLimpiar; }

    public void rellenar(String n, String a, String t) {
        nombre.setText(n);
        apellido.setText(a);
        telefono.setText(t);
    }

    public void limpiar() {
        rellenar("", "", "");
    }

    private static void estilo(JButton b, String fondo, String texto) {
        b.putClientProperty(FlatClientProperties.STYLE,
                "background: " + fondo + "; foreground: " + texto);
    }
}