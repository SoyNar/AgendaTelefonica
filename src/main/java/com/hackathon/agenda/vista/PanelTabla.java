package com.hackathon.agenda.vista;

import com.formdev.flatlaf.FlatClientProperties;
import com.hackathon.agenda.modelo.Contacto;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

public class PanelTabla extends PanelTarjeta {

    private static final String CARD_TABLA = "tabla";
    private static final String CARD_VACIO = "vacio";

    private final DefaultTableModel modelo;
    private final JTable tabla;
    private final TableRowSorter<DefaultTableModel> sorter;
    private final JTextField filtro;
    private final CardLayout cartas = new CardLayout();
    private final JPanel centro = new JPanel(cartas);

    public PanelTabla() {
        super(new BorderLayout(0, 12), 22, new Color(20, 12, 44, 150));
        setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));

        modelo = new DefaultTableModel(new Object[]{"Nombre", "Apellido", "Teléfono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(46);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 38));
        tabla.getTableHeader().setFont(tabla.getFont().deriveFont(Font.BOLD, 12f));

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }
        tabla.getColumnModel().getColumn(0).setCellRenderer(new AvatarRenderer());

        // Filtro: solo cambia lo que se ve. Desactivar el orden evita desfasar índices con el controlador.
        sorter = new TableRowSorter<>(modelo);
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            sorter.setSortable(i, false);
        }
        tabla.setRowSorter(sorter);

        filtro = new JTextField();
        filtro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "🔍  Filtrar por nombre, apellido o teléfono");
        filtro.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e)  { aplicarFiltro(); }
            @Override public void removeUpdate(DocumentEvent e)  { aplicarFiltro(); }
            @Override public void changedUpdate(DocumentEvent e) { aplicarFiltro(); }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        JLabel vacioTitulo = new JLabel("Aún no tienes contactos", SwingConstants.CENTER);
        vacioTitulo.setFont(vacioTitulo.getFont().deriveFont(Font.BOLD, 14f));
        vacioTitulo.setForeground(new Color(245, 243, 255));
        vacioTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel vacioSub = new JLabel("Escribe un nombre y pulsa Guardar para empezar", SwingConstants.CENTER);
        vacioSub.setForeground(new Color(167, 139, 250));
        vacioSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel vacio = new JPanel();
        vacio.setLayout(new BoxLayout(vacio, BoxLayout.Y_AXIS));
        vacio.setOpaque(false);
        vacio.add(Box.createVerticalGlue());
        vacio.add(vacioTitulo);
        vacio.add(Box.createVerticalStrut(6));
        vacio.add(vacioSub);
        vacio.add(Box.createVerticalGlue());

        centro.setOpaque(false);
        centro.add(scroll, CARD_TABLA);
        add(filtro, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
    }

    private void aplicarFiltro() {
        String texto = filtro.getText().trim();
        sorter.setRowFilter(texto.isEmpty() ? null
                : RowFilter.regexFilter("(?i)" + Pattern.quote(texto)));
    }

    public void cargar(List<Contacto> contactos) {
        modelo.setRowCount(0);
        for (Contacto c : contactos) {
            modelo.addRow(new Object[]{c.getNombre(), c.getApellido(), c.getTelefono()});
        }
        cartas.show(centro, contactos.isEmpty() ? CARD_VACIO : CARD_TABLA);
    }

    public void limpiarSeleccion() { tabla.clearSelection(); }

    /** Devuelve el índice en el modelo (no en la vista), así coincide con los datos aunque haya filtro. */
    public int getFilaSeleccionada() {
        int vista = tabla.getSelectedRow();
        return vista < 0 ? -1 : tabla.convertRowIndexToModel(vista);
    }

    public JTable getTabla()             { return tabla; }
    public DefaultTableModel getModelo() { return modelo; }

}