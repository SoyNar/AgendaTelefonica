package com.hackathon.agenda;

import com.formdev.flatlaf.FlatDarkLaf;
import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.vista.VistaPrueba;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Proyecto base listo");
        UIManager.put("Component.arc",14);
        UIManager.put("Button.arc",14);
        UIManager.put("TextComponent.arc",14);


        VistaPrueba.paletaColores();
        FlatDarkLaf.setup();


        SwingUtilities.invokeLater(() -> {
            VistaPrueba vista = new VistaPrueba();
            new AgendaControlador(vista);
            vista.setVisible(true);
        });

    }
}
