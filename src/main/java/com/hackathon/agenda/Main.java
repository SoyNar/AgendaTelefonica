package com.hackathon.agenda;

import com.formdev.flatlaf.FlatDarkLaf;
import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.vista.Tema;
import com.hackathon.agenda.vista.VistaPrueba;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

            Tema.aplicar();

            SwingUtilities.invokeLater(() -> {
                VistaPrueba vista = new VistaPrueba();
                new AgendaControlador(vista);
                vista.setVisible(true);
            });

    }
}
