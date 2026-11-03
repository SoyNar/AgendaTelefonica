package com.hackathon.agenda.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Time;

/**
 * Esta clase es una fabria de componentes reusables de swing
 * Da metodos estaticos para la creacion de campos, botones, tablas y panles
 * las variables de color pueden reusarse desde otras clases
 * */
public final class ComponentesGraficos {

    private static final Color COLOR_ERROR = Color.RED;
    public static final Color COLOR_EXITO = new Color(1,128,0);
    private  static final  int ESPACIO_FILAS = 5;

    private ComponentesGraficos(){}

    /**
     * Crea un campo de texto de una sola linea
     *
     * @param columnas ancho aproximado  del campo, en caracteres
     * @return campo de texto listo  para usarse en el formulario
     * * */
    public  static JTextField crearCampoTexto(int columnas){
        JTextField campo = new JTextField(columnas);
        campo.putClientProperty("JTextField.placeholderText", "Escribe aquí");
        return campo;
    }

    /**
     * Crear un bloque de formulario con la etiqueta arriba
     *
     * @param etiqueta texzto que describe el campo
     * @param campo component3e de entrada
     * @return panel con etiqueta y campos apilados
     * */

    public static JPanel crearComponenteFila(String etiqueta, JComponent campo){
        JPanel bloque = new JPanel(new BorderLayout(0,3));
        bloque.add(new JLabel(etiqueta),BorderLayout.NORTH);
        bloque.add(campo,BorderLayout.CENTER);
        return  bloque;
    }

    /**
     * Agrupar bloques de forumario en una fila con el mismo ancho
     *
     * @param bloques paneles creados {@link}
     * @return panel con los bloques en fila
     * */

    public static JPanel creaFilaCampos(JPanel... bloques)
    {
        JPanel fila = new JPanel(new GridLayout(1,bloques.length,10,0));
        for(JPanel bloque: bloques){
            fila.add(bloque);
        }
        return  fila;

    }

    /**
     * Crear el panel de estado
     *
     * @param etiqueta etiqueta donde se escriben los mensajes de error o exito
     * return panel de estao con margenes
     *
     * */

    public static JPanel crearPanelEstado(JLabel etiqueta){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));
        panel.add(etiqueta,BorderLayout.CENTER);
        return  panel;
    }




    /**
     * Crear un botton con el texto indicado
     *
     * @param texto etiqueta visible del boton
     * @return boton sin accion asignada
     * */

    public static JButton creatBoton(String texto){
        return  new JButton(texto);
    }

    /**
     * Agrupar botones en una fila cerrada
     *
     * @param botones botones  a mostrar en el orden recibido
     * @return panel con los botones distribuidos
     * */

    public static JPanel crearPanelBotones (JButton... botones){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (JButton boton : botones){
            panel.add(boton);
        }
        return  panel;
    }


    /**
     * Crear un modelo de tabla en el que ninguna celda es editable
     * los datos solo cambian desde el contolador , a traves dde la vista
     *
     * @param columnas nombre de la columna en orden de presetacion
     * @return modelo de tabla no editabl3e
     * */

    public static DefaultTableModel crearModeloTabla (String... columnas){
        return  new  DefaultTableModel(columnas,0){
            @Override
            public boolean isCellEditable(int fila, int columna){
                return  false;
            }
        };
    }


    /**
     * Crear una tabla que permit3e seleccionar una sola fila a la vex
     *
     * @param modelo modelo  de datos de la tabla
     * @return tabla configurada para seledccion simple
     * */
    public static JTable crearTabla (DefaultTableModel modelo){
        JTable tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(32);
        tabla.setShowHorizontalLines(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        return  tabla;
    }

    /**
     * Crear etiqueta del panel de estado
     *
     * @return etiqueta para recibir mensajes
     * */

    public static JLabel crearEtiquetaEstado(){
        return  new JLabel("");
    }


    /**
     * Muestra un mensaje en una etiqueta de estado en un color indicado
     *
     * @param etiqueta etiquesta de estado donde se esscribe el mensaje
     * @param texto mensaje que se muestra
     * @param color
     *
     * */

    public static void mostrarMensaje(JLabel etiqueta, String texto, Color color){
        etiqueta.setForeground(color);
        etiqueta.setText(texto);
        Timer t = new Timer(3000,e -> etiqueta.setText(""));
        t.start();
    }

    /**
     *metodo para crear boton con estilo pripio
     * */
    public static JButton creatBotonPrimario(String texto){
        JButton boton = new JButton(texto);
        boton.putClientProperty("FlatLaf.style",
                "background: #6c8cff; foreground: #ffffff; arc: 12");
        return boton;
    }

    public static JButton creatBotonPeligro(String texto){
        JButton boton = new JButton(texto);
        boton.putClientProperty("FlatLaf.style",
                "foreground: #ef5b5b; borderColor: #ef5b5b; arc: 12");
        return boton;
    }

}
