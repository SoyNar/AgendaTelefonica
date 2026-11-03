package com.hackathon.agenda.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // Importamos el modelo de tabla
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    // 1. VARIABLES PÚBLICAS PARA EL FORMULARIO
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtTelefono;
    public JButton btnAgregar;
    public JButton btnModificar;
    public JButton btnEliminar;
    public JButton btnBuscar;

    // 2. VARIABLES PÚBLICAS PARA LA TABLA Y EL ESTADO
    public JTable tablaContactos;
    public DefaultTableModel modeloTabla;
    public JLabel lblEstado;

    public VentanaPrincipal() {
        setTitle("Agenda Telefónica MVC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // archivo principal

        // CAJÓN DE ARRIBA

        JPanel panelNorte = new JPanel(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 10));
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtTelefono = new JTextField();
        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Apellido:"));
        panelCampos.add(txtApellido);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(txtTelefono);

        JPanel panelBotones = new JPanel();
        btnAgregar = ComponentesGraficos.crearBotonPrincipal("Guardar");
        btnBuscar = ComponentesGraficos.crearBotonPrincipal("Buscar");
        btnModificar = ComponentesGraficos.crearBotonPrincipal("Modificar");
        btnEliminar = ComponentesGraficos.crearBotonPeligro("Eliminar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);

        panelNorte.add(panelCampos, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);
        add(panelNorte, BorderLayout.NORTH);

        // CAJÓN DEL MEDIO

        String[] columnas = {"Nombre", "Apellido", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0);


        tablaContactos = new JTable(modeloTabla);

        JScrollPane scrollTabla = new JScrollPane(tablaContactos);

        add(scrollTabla, BorderLayout.CENTER);

        // CAJÓN DE ABAJO

        lblEstado = new JLabel("Esperando acción...");

        lblEstado.setFont(new Font("Arial", Font.BOLD, 14));

        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

        add(lblEstado, BorderLayout.SOUTH);
    }
}