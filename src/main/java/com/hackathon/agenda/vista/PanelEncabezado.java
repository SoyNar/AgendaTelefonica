package com.hackathon.agenda.vista;

import javax.swing.*;
import java.awt.*;

public class PanelEncabezado extends JPanel {

    private final JLabel lblConteo;

    public PanelEncabezado() {
        super(new BorderLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(24, 32, 4, 32));

        JLabel titulo = new JLabel("Mis contactos");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));
        titulo.setForeground(new Color(245, 243, 255));

        lblConteo = new JLabel("0 contactos");
        lblConteo.setFont(lblConteo.getFont().deriveFont(Font.PLAIN, 12f));
        lblConteo.setForeground(new Color(167, 139, 250));

        add(titulo, BorderLayout.WEST);
        add(lblConteo, BorderLayout.SOUTH);
    }

    public void actualizarConteo(int total) {
        lblConteo.setText(total + (total == 1 ? " contacto" : " contactos"));
    }
}