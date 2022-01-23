package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesion17a extends JFrame {

    private JTextField nombre;
    private JPasswordField contrasena;

    public IniciarSesion17a() {

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

        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new FlowLayout());

        JLabel textoNombre = new JLabel("Nombre:");
        textoNombre.setForeground(Config.FONT_COLOR);
        this.add(textoNombre);
        nombre = new JTextField("", 10);
        this.add(nombre);

        JLabel textoContrasena = new JLabel("Contraseña:");
        textoContrasena.setForeground(Config.FONT_COLOR);
        this.add(textoContrasena);
        contrasena = new JPasswordField("", 10);
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
                new CorreoRecuperacion17b();
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
                dispose();
                new Inicio16();
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
                JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarInicio(nombre.getText(), String.valueOf(contrasena.getPassword()));
//                * Definición de JSON:
//                { : boolean, : String }
//                    Si es True, String = nombreUsuario
//                    Si es False, String = mensaje de error correspondiente

                if (!resultado.getBoolean("estado")) {

                    new MensajeError((String) resultado.get("mensaje"), false);

                } else {

                    new MenuPrincipal18((String) resultado.get("mensaje"));
                }

            }
        });
        return iniciar;
    }
}
