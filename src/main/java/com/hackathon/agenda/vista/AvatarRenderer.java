package com.hackathon.agenda.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class AvatarRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable t, Object value, boolean sel, boolean foc, int row, int col) {
        JLabel lbl = (JLabel) super.getTableCellRendererComponent(t, value, sel, foc, row, col);
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setIcon(new IconoIniciales(String.valueOf(value)));
        lbl.setIconTextGap(12);
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
        return lbl;
    }

    static class IconoIniciales implements Icon {
        private static final int T = 30;
        private final String inicial;

        IconoIniciales(String texto) {
            inicial = (texto == null || texto.isBlank()) ? "?" : texto.substring(0, 1).toUpperCase();
        }

        public int getIconWidth()  { return T; }
        public int getIconHeight() { return T; }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(x, y, new Color(124, 58, 237), x + T, y + T, new Color(236, 72, 153)));
            g2.fillOval(x, y, T, T);
            g2.setColor(Color.WHITE);
            g2.setFont(c.getFont().deriveFont(Font.BOLD, 13f));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(inicial, x + (T - fm.stringWidth(inicial)) / 2,
                    y + (T + fm.getAscent() - fm.getDescent()) / 2);
            g2.dispose();
        }
    }
}