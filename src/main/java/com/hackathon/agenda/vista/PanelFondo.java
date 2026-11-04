package com.hackathon.agenda.vista;

import javax.swing.*;
import java.awt.*;

public class PanelFondo extends JPanel {

    public PanelFondo() {
        super(new BorderLayout());
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2.setPaint(new GradientPaint(0, 0, new Color(58, 20, 110), w, h, new Color(12, 7, 28)));
        g2.fillRect(0, 0, w, h);

        float radio = Math.max(w, h) * 0.6f;
        g2.setPaint(new RadialGradientPaint(new Point(w - 60, 40), radio,
                new float[]{0f, 1f},
                new Color[]{new Color(124, 58, 237, 110), new Color(124, 58, 237, 0)}));
        g2.fillRect(0, 0, w, h);

        g2.setPaint(new RadialGradientPaint(new Point(40, h - 20), radio * 0.7f,
                new float[]{0f, 1f},
                new Color[]{new Color(236, 72, 153, 70), new Color(236, 72, 153, 0)}));
        g2.fillRect(0, 0, w, h);

        g2.dispose();
        super.paintComponent(g);
    }
}