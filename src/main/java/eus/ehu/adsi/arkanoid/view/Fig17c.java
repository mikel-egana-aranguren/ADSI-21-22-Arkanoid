package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fig17c extends JFrame {

    private String correo;
    private String codigo;
    private JTextField codigoIntroducido;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;

    public Fig17c(String pCorreo, String pCodigo) {

        this.correo = pCorreo;
        this.codigo = pCodigo;
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

        JLabel textoCorreo = new JLabel("Se ha enviado un correo con el código para cambiar la contraseña");
        textoCorreo.setForeground(Config.FONT_COLOR);
        this.add(textoCorreo);

        JLabel textoCodigo = new JLabel("Código:");
        textoCodigo.setForeground(Config.FONT_COLOR);
        this.add(textoCodigo);
        codigoIntroducido = new JTextField("", 10);
        this.add(codigoIntroducido);

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

        JLabel textoVolver = new JLabel("¿No te ha llegado el código?:");
        textoVolver.setForeground(Config.FONT_COLOR);
        this.add(textoVolver);
        this.add(botonVolver());

        this.add(botonAceptar());
        this.add(botonCancelar());
    }

    private JButton botonVolver() {

        JButton volver = new JButton("Volver a enviar");
        volver.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String codigo = ArkanoidFrontera.getArkanoidFrontera().enviarEmail(correo);
                new Fig17c(correo, codigo);
            }
        });
        return volver;
    }

    private JButton botonAceptar() {

        JButton aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCodigo(correo, codigo, codigoIntroducido.getText(), String.valueOf(contrasena1.getPassword()), String.valueOf(contrasena2.getPassword()));
//                * Definición de JSON:
//                { : boolean, : String }
//                    Si es True, String = vacío
//                    Si es False, String = mensaje de error correspondiente

                if (!resultado.getBoolean("estado")) {

                    new MensajeError((String) resultado.get("mensaje"), false);

                } else {

                    new Fig17a();
                }
            }
        });
        return aceptar;
    }

    private JButton botonCancelar() {

        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Fig17b();
            }
        });
        return cancelar;
    }
}
