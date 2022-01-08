package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fig20 extends JFrame {

    private String nombreUsuario;
    private JPasswordField contrasenaOriginal;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;

    public Fig20(String pNombreUsuario) {

        this.nombreUsuario = pNombreUsuario;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Cambiar contraseña");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void drawScene() {

        this.getContentPane().setBackground(Config.BACKGROUND_COLOR);
        this.setLayout(new FlowLayout());

        JLabel textoCambiarContrasena = new JLabel("Cambiar contraseña");
        textoCambiarContrasena.setForeground(Config.FONT_COLOR);
        this.add(textoCambiarContrasena);

        JLabel textoNombreUsuario = new JLabel(nombreUsuario);
        textoNombreUsuario.setForeground(Config.FONT_COLOR);
        this.add(textoNombreUsuario);

        JLabel textoContrasenaOriginal = new JLabel("Antigua contraseña:");
        textoContrasenaOriginal.setForeground(Config.FONT_COLOR);
        this.add(textoContrasenaOriginal);
        contrasenaOriginal = new JPasswordField("", 10);
        this.add(contrasenaOriginal);

        JLabel textoContrasena1 = new JLabel("Nueva contraseña:");
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
        this.add(botonAceptar());
    }

    private JButton botonCancelar() {

        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Fig18(nombreUsuario);
            }
        });
        return cancelar;
    }

    private JButton botonAceptar() {

        JButton aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio(nombreUsuario, String.valueOf(contrasenaOriginal.getPassword()), String.valueOf(contrasena1.getPassword()), String.valueOf(contrasena2.getPassword()));

                if (!resultado.getBoolean("estado")) {

                    new MensajeError((String) resultado.get("mensaje"), false);

                } else {

                    new Fig18((String) resultado.get("mensaje"));
                }
            }
        });
        return aceptar;
    }
}
