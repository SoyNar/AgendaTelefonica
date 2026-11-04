package com.hackathon.agenda.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PanelTarjeta extends JPanel {

    private final int arco;
    private final Color relleno;

    public PanelTarjeta(LayoutManager layout, int arco, Color relleno) {
        super(layout);
        this.arco = arco;
        this.relleno = relleno;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D forma = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arco, arco);
        g2.setColor(relleno);
        g2.fill(forma);
        g2.setColor(new Color(255, 255, 255, 28));
        g2.draw(forma);
        g2.dispose();
        super.paintComponent(g);
    }
}