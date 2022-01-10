package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorreoRecuperacion17b extends JFrame {

    private JTextField correo;

    public CorreoRecuperacion17b() {

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
                new Fig16();
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
                JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().recuperarContrasena(correo.getText());
//              * Definición de JSON:
//              { : boolean, : String }
//                  Si es True, String = código que se ha enviado al correo
//                  Si es False, String = mensaje de error correspondiente

                if (!resultado.getBoolean("estado")) {

                    new MensajeError((String) resultado.get("mensaje"), false);

                } else {

                    new Fig17c(correo.getText(), (String) resultado.get("mensaje"));
                }
            }
        });
        return enviar;
    }
}
