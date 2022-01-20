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

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {

        this.getContentPane().setBackground(Config.BACKGROUND_COLOR);
        this.setLayout(new GridLayout(3,0));

        JLabel textoCorreo = new JLabel("Introduce el correo asociado a tu cuenta para recuperar la contraseña:");
        textoCorreo.setForeground(Config.FONT_COLOR);
        JPanel texto = new JPanel();
        texto.setLayout(new GridBagLayout());
        texto.setBackground(Config.BACKGROUND_COLOR);
        texto.add(textoCorreo);
        this.add(texto);

        correo = new JTextField("", 20);
        JPanel campo = new JPanel();
        campo.setBackground(Config.BACKGROUND_COLOR);
        campo.add(correo);
        this.add(campo);

        JPanel botones = new JPanel();
        botones.setBackground(Config.BACKGROUND_COLOR);
        botones.add(botonCancelar());
        botones.add(botonEnviar());
        this.add(botones);
    }

    //Método para crear el botón de cancelar, que abrirá la pantalla de Inicio
    private JButton botonCancelar() {

        JButton cancelar = new JButton("Cancelar");
        cancelar.setFont(Font.getFont(Config.FONT));
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cerrarActual();
                new Fig16();
            }
        });
        return cancelar;
    }

    //Método para crear el botón de enviar, que mandará a comprobar los datos
    private JButton botonEnviar() {

        JButton enviar = new JButton("Enviar correo");
        enviar.setFont(Font.getFont(Config.FONT));
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

                //Comprobar el estado de la recuperación
                if (!resultado.getBoolean("estado")) {
                    //Si ha sido incorrecto mostrar mensaje de error, con el mensaje que corresponda
                    new MensajeError((String) resultado.get("mensaje"), false);

                } else {
                    //Si ha sido exitoso, cerrar pantalla actual
                    cerrarActual();
                    //Abrir pantalla de Recuperar contraseña con el correo del usuario, y pasando el código que se ha mandado al email
                    new RecuperarContrasena17c(correo.getText(), (String) resultado.get("mensaje"));
                }
            }
        });
        return enviar;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
