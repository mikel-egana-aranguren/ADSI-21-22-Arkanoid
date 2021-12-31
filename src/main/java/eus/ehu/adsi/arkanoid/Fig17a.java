package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.view.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fig17a extends JFrame {

    private JTextField nombre;
    private JTextField contrasena;

    public Fig17a() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Iniciar sesión");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void drawScene() {

        this.getContentPane().setBackground(Config.BACKGROUND_COLOR);
        this.setLayout(new FlowLayout());

        JLabel textoNombre = new JLabel("Nombre:");
        textoNombre.setForeground(Config.FONT_COLOR);
        this.add(textoNombre);
        nombre = new JTextField("", 10);
        this.add(nombre);

        JLabel textoContrasena = new JLabel("Contraseña:");
        textoContrasena.setForeground(Config.FONT_COLOR);
        this.add(textoContrasena);
        contrasena = new JTextField("", 10);
        this.add(contrasena);

        this.add(botonOlvidar());
        this.add(botonCancelar());
        this.add(botonIniciar());
    }

    private JButton botonOlvidar() {

        JButton olvidar = new JButton("He olvidado mi contraseña");
        olvidar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Olvidar");
                new Fig17b();
            }
        });
        return olvidar;
    }

    private JButton botonCancelar() {

        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Cancelar");
                //new Fig16();
            }
        });
        return cancelar;
    }

    private JButton botonIniciar() {

        JButton iniciar = new JButton("Iniciar Sesión");
        iniciar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Iniciar");

//                int resultado = FronteraArkanoid.comprobarInicio(nombre.getText(), contrasena.getText());
//                * Definición de JSON:
//                { : boolean, : String }
//                    Si es True, String = nombreUsuario
//                    Si es False, String = mensaje de error correspondiente
//
//                if (false) {
//
//                    new MensajeError(texto);
//
//                } else {
//
//                    new Fig18(texto);
//                }

            }
        });
        return iniciar;
    }
}
