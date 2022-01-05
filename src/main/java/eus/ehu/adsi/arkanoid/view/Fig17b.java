package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.view.game.Config;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fig17b extends JFrame {

    private JTextField correo;

    public Fig17b() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Recuperar Contraseña");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void drawScene() {

        this.getContentPane().setBackground(Config.BACKGROUND_COLOR);
        this.setLayout(new FlowLayout());

        JLabel textoCorreo = new JLabel("Introduce el correo asociado a tu cuenta para recuperar la contraseña:");
        textoCorreo.setForeground(Config.FONT_COLOR);
        this.add(textoCorreo);

        correo = new JTextField("", 10);
        this.add(correo);

        this.add(botonCancelar());
        this.add(botonEnviar());
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

    private JButton botonEnviar() {

        JButton enviar = new JButton("Enviar correo");
        enviar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Enviar correo");



               //Enviar email




                new Fig17c("", "");
            }
        });
        return enviar;
    }
}
