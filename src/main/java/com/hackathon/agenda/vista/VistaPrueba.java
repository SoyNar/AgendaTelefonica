package com.hackathon.agenda.vista;

import com.formdev.flatlaf.FlatDarkLaf;
import com.hackathon.agenda.modelo.Contacto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class VistaPrueba extends JFrame {



//    public static void main(String[] args)
//    {
//
//        //redondear bordes de componnet3es
//        UIManager.put("Component.arc",14);
//        UIManager.put("Button.arc",14);
//        UIManager.put("TextComponent.arc",14);
//        paletaColores();
//
//        FlatDarkLaf.setup();
//        SwingUtilities.invokeLater(() -> {
//            VistaPrueba vista = new VistaPrueba();
//            vista.setVisible(true);
//        });
//
//    }

    //componentes de entrada   (cajas de txto)

    private JTextField name;
    private  JTextField lastname;
    private  JTextField phone;

    private  JLabel lblEstado;

    private JButton btnGuardar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;


    public  static void paletaColores()
    {
        //definiendo una paleta de colores
        Map<String,String> colores = new HashMap<>();
        colores.put("@background", "#140a2e"); //fondo
        colores.put("@foreground", "#E2E8F0"); //t4xtp principal
        colores.put("@accentColor","#7c3aed"); //color de acento
        colores.put("TextComponent.background", "#1e143d"); //fonde de los container de t4xto
        colores.put("Table.background", "#1e143d"); //fondo de tabla
        colores.put("Table.alternateRowColor", "#170e30"); //tipo cebra
        FlatDarkLaf.setGlobalExtraDefaults(colores);


    }

    public VistaPrueba(){
        setTitle("Agenda Telefónica - Hackathon Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 520);
        setLocationRelativeTo(null);
        incializarComponente();

    }

    private void incializarComponente() {
        name = ComponentesGraficos.crearCampoTexto(15, "Ej: Carlos");
        lastname = ComponentesGraficos.crearCampoTexto(15, "Ej: García");
        phone = ComponentesGraficos.crearCampoTexto(15, "Ej: 600 000 000");

        JPanel filaForm = ComponentesGraficos.creaFilaCampos(
                ComponentesGraficos.crearComponenteFila("Nombre", name),
                ComponentesGraficos.crearComponenteFila("Apellido", lastname),
                ComponentesGraficos.crearComponenteFila("Teléfono", phone));

        btnGuardar = ComponentesGraficos.creatBotonPrimario("Guardar");
        btnEditar = ComponentesGraficos.creatBoton("Editar");
        btnEliminar = ComponentesGraficos.creatBotonPeligro("Eliminar");
        btnBuscar = ComponentesGraficos.creatBoton("Buscar");
        btnLimpiar = ComponentesGraficos.creatBoton("Limpiar");

        modeloTabla = ComponentesGraficos.crearModeloTabla("Nombre", "Apellido", "Teléfono");
        tablaContactos = ComponentesGraficos.crearTabla(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaContactos);
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));

        lblEstado = ComponentesGraficos.crearEtiquetaEstado();
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelBotones = ComponentesGraficos.crearPanelBotones(
                btnGuardar, btnEditar, btnEliminar, btnBuscar, btnLimpiar);

        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contenedor.add(filaForm);
        contenedor.add(Box.createVerticalStrut(15));
        contenedor.add(panelBotones);

        setLayout(new BorderLayout());
        add(contenedor, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(ComponentesGraficos.crearPanelEstado(lblEstado), BorderLayout.SOUTH);
    }

    public JTextField getTxtNombre() { return name; }
    public JTextField getTxtApellido() { return lastname; }
    public JTextField getTxtTelefono() { return phone; }

    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }

    public JTable getTablaContactos() { return tablaContactos; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }

    /**
     * MEtodo para mostrar mensaje de error
     * */
    public void mostrarMensajeError(String mensaje) {
        lblEstado.setForeground(new Color(239, 68, 68));
        lblEstado.setText(mensaje);
    }
    /**
     * MEtodo para mostrar mensaje de exito
     * */

    public void mostrarMensajeExito(String mensaje) {
        lblEstado.setForeground(new Color(34, 197, 94)); // Verde
        lblEstado.setText(mensaje);
    }

    /** Indice de la fila seleccionada, o -1 si no hay ninguna. */
    public int getFilaSeleccionada() {
        return tablaContactos.getSelectedRow();
    }

    /** Reemplaza el contenido de la tabla con la lista recibida. */
    public void cargarTabla(List<Contacto> contactos) {
        modeloTabla.setRowCount(0);
        for (Contacto c : contactos) {
            modeloTabla.addRow(new Object[]{c.getNombre(), c.getApellido(), c.getTelefono()});
        }
    }

    /** Copia los datos de un contacto al formulario. */
    public void rellenarFormulario(String nombre, String apellido, String telefono) {
        name.setText(nombre);
        lastname.setText(apellido);
        phone.setText(telefono);
    }

    /** Vacia los campos y quita la seleccion de la tabla. */
    public void limpiarFormulario() {
        name.setText("");
        lastname.setText("");
        phone.setText("");
        tablaContactos.clearSelection();
    }
}


