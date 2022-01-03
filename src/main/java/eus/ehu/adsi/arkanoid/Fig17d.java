package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.view.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fig17d extends JFrame {

    private JTextField nombre;
    private JTextField correo;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;

    public Fig17d() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Registrarse");
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

        JLabel textoCorreo = new JLabel("Correo:");
        textoCorreo.setForeground(Config.FONT_COLOR);
        this.add(textoCorreo);
        correo = new JTextField("", 10);
        this.add(correo);

        JLabel textoContrasena1 = new JLabel("Contraseña:");
        textoContrasena1.setForeground(Config.FONT_COLOR);
        this.add(textoContrasena1);
        contrasena1 = new JPasswordField("", 10);
        this.add(contrasena1);

        JLabel textoContrasena2 = new JLabel("Confirmar contraseña:");
        textoContrasena2.setForeground(Config.FONT_COLOR);
        this.add(textoContrasena2);
        contrasena2 = new JPasswordField("", 10);
        this.add(contrasena2);

        this.add(botonCancelar());
        this.add(botonCrear());
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

    private JButton botonCrear() {

        JButton crear = new JButton("Crear cuenta");
        crear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Crear cuenta");
                //Comprobar
                //new Fig18("");
            }
        });
        return crear;
    }
}
