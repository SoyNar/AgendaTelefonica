import com.hackathon.agenda.vista.ComponentesGraficos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentesGraficosTest {

    @Test
    @DisplayName("crearCampoTexto debe retornar un JTextField configurado con las columnas indicadas")
    void testCrearCampoTexto() {
        JTextField campo = ComponentesGraficos.crearCampoTexto(15,"Aqui");

        assertNotNull(campo, "El campo de texto no debe ser nulo");
        assertEquals(15, campo.getColumns(), "El número de columnas asignado debe ser 15");
    }

    @Test
    @DisplayName("crearComponenteFila debe agrupar la etiqueta y el campo dentro de un JPanel")
    void testCrearComponenteFila() {
        JTextField campoPrueba = new JTextField(10);
        JPanel bloque = ComponentesGraficos.crearComponenteFila("Nombre:", campoPrueba);

        assertNotNull(bloque, "El panel de bloque no debe ser nulo");
        assertEquals(2, bloque.getComponentCount(), "Debe contener exactamente 2 componentes (JLabel y JTextField)");
        assertTrue(bloque.getLayout() instanceof BorderLayout, "El diseño debe ser BorderLayout");
    }

    @Test
    @DisplayName("creaFilaCampos debe organizar varios bloques en un panel en fila")
    void testCreaFilaCampos() {
        JPanel b1 = new JPanel();
        JPanel b2 = new JPanel();
        JPanel b3 = new JPanel();

        JPanel fila = ComponentesGraficos.creaFilaCampos(b1, b2, b3);

        assertNotNull(fila, "El panel de fila no debe ser nulo");
        assertEquals(3, fila.getComponentCount(), "Debe contener los 3 bloques agregados");
        assertTrue(fila.getLayout() instanceof GridLayout, "Debe utilizar un GridLayout para la distribución");
    }

    @Test
    @DisplayName("creatBoton debe instanciar un JButton con el texto correspondiente")
    void testCreatBoton() {
        JButton boton = ComponentesGraficos.creatBoton("Guardar");

        assertNotNull(boton, "El botón no debe ser nulo");
        assertEquals("Guardar", boton.getText(), "El texto visible del botón debe ser 'Guardar'");
    }

    @Test
    @DisplayName("crearModeloTabla debe retornar un DefaultTableModel NO editable por el usuario")
    void testCrearModeloTablaNoEditable() {
        DefaultTableModel modelo = ComponentesGraficos.crearModeloTabla("Nombre", "Apellido", "Teléfono");

        assertNotNull(modelo, "El modelo de tabla no debe ser nulo");
        assertEquals(3, modelo.getColumnCount(), "Debe tener 3 columnas");

        // Simular una fila de datos
        modelo.addRow(new Object[]{"Juan", "Pérez", "123456"});

        // Verificación: NINGUNA celda puede editarse directamente
        assertFalse(modelo.isCellEditable(0, 0), "La celda (0,0) no debe ser editable");
        assertFalse(modelo.isCellEditable(0, 1), "La celda (0,1) no debe ser editable");
        assertFalse(modelo.isCellEditable(0, 2), "La celda (0,2) no debe ser editable");
    }

    @Test
    @DisplayName("crearTabla debe configurar la JTable con selección simple de filas")
    void testCrearTabla() {
        DefaultTableModel modelo = ComponentesGraficos.crearModeloTabla("Col1", "Col2");
        JTable tabla = ComponentesGraficos.crearTabla(modelo);

        assertNotNull(tabla, "La tabla no debe ser nula");
        assertEquals(ListSelectionModel.SINGLE_SELECTION, tabla.getSelectionModel().getSelectionMode(),
                "El modo de selección debe ser estricto de una sola fila (SINGLE_SELECTION)");
        assertTrue(tabla.getFillsViewportHeight(), "FillsViewportHeight debe estar en true");
    }

    @Test
    @DisplayName("mostrarMensaje debe cambiar el texto y color del JLabel de estado")
    void testMostrarMensaje() {
        JLabel etiqueta = ComponentesGraficos.crearEtiquetaEstado();
        ComponentesGraficos.mostrarMensaje(etiqueta, "Error de prueba", Color.RED);

        assertEquals("Error de prueba", etiqueta.getText(), "El texto de la etiqueta debe actualizarse");
        assertEquals(Color.RED, etiqueta.getForeground(), "El color asignado debe ser el indicado");
    }
}
