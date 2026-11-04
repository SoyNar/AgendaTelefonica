package com.hackathon.agenda.vista;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class Tema {

    private Tema() {}

    /** Llamar una sola vez, antes de crear cualquier ventana. */
    public static void aplicar() {
        UIManager.put("Component.arc", 14);
        UIManager.put("Button.arc", 999);
        UIManager.put("TextComponent.arc", 12);
        UIManager.put("Component.focusWidth", 1);
        UIManager.put("Component.innerFocusWidth", 0);
        UIManager.put("Button.margin", new Insets(9, 22, 9, 22));
        UIManager.put("Table.rowHeight", 46);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("ScrollBar.width", 10);
        UIManager.put("ScrollBar.thumbArc", 999);

        paleta();
        FlatDarkLaf.setup();

    }

    private static void paleta() {
        Map<String, String> colores = new HashMap<>();


        colores.put("@background", "#140a2e");
        colores.put("@foreground", "#E2E8F0");
        colores.put("@accentColor", "#7c3aed");
        colores.put("Component.accentColor", "#7c3aed");
        colores.put("TextComponent.hoverBorderColor", "#3b2a6b");   // igual a TextComponent.borderColor
        colores.put("TextComponent.borderColor", "#3b2a6b");

        colores.put("TextComponent.background", "#1b1238");
        colores.put("TextComponent.borderColor", "#3b2a6b");
        colores.put("Button.background", "#2a1d52");
        colores.put("Button.foreground", "#E2E8F0");
        colores.put("Button.default.background", "#7c3aed");
        colores.put("Button.default.hoverBackground", "#8b5cf6");
        colores.put("Button.default.pressedBackground", "#6d28d9");
        colores.put("Button.default.foreground", "#ffffff");

        colores.put("Table.background", "#1e143d");
        colores.put("Table.alternateRowColor", "#231646");
        colores.put("Table.gridColor", "#2e2058");
        colores.put("Table.selectionBackground", "#6d28d9");
        colores.put("Table.selectionForeground", "#ffffff");
        colores.put("TableHeader.background", "#2b1a5c");
        colores.put("TableHeader.foreground", "#c4b5fd");

        FlatDarkLaf.setGlobalExtraDefaults(colores);
    }
}