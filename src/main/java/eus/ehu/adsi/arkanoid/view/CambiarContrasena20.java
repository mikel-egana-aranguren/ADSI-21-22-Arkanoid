package eus.ehu.adsi.arkanoid.view;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Config;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarContrasena20 extends JFrame {

    private String nombreUsuario;
    private JPasswordField contrasenaOriginal;
    private JPasswordField contrasena1;
    private JPasswordField contrasena2;

    public CambiarContrasena20(String pNombreUsuario) {

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

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {

        this.setLayout(new BorderLayout());

        JPanel titulo = new JPanel();
        titulo.setBackground(Config.BACKGROUND_COLOR);
        titulo.setLayout(new GridLayout(2,0));

        JLabel textoCambiarContrasena = new JLabel("Cambiar contraseña");
        textoCambiarContrasena.setForeground(Config.FONT_COLOR);
        titulo.add(textoCambiarContrasena);

        JLabel textoNombreUsuario = new JLabel(nombreUsuario);
        textoNombreUsuario.setForeground(Color.RED);
        titulo.add(textoNombreUsuario);

        JPanel contenedor = new JPanel();
        contenedor.setBackground(Config.BACKGROUND_COLOR);
        contenedor.setLayout(new GridBagLayout());
        contenedor.add(titulo);

        this.add(contenedor, BorderLayout.PAGE_START);

        JPanel campos = new JPanel();
        campos.setBackground(Config.BACKGROUND_COLOR);
        campos.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoContrasenaOriginal = new JLabel("Antigua contraseña:");
        textoContrasenaOriginal.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 0;
        campos.add(textoContrasenaOriginal, c);
        contrasenaOriginal = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 0;
        campos.add(contrasenaOriginal, c);

        JLabel textoContrasena1 = new JLabel("Nueva contraseña:");
        textoContrasena1.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 1;
        campos.add(textoContrasena1, c);
        contrasena1 = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 1;
        campos.add(contrasena1, c);

        JLabel textoContrasena2 = new JLabel("Confirmar contraseña:");
        textoContrasena2.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 2;
        campos.add(textoContrasena2, c);
        contrasena2 = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 2;
        campos.add(contrasena2, c);

        this.add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Config.BACKGROUND_COLOR);
        botones.add(botonCancelar());
        botones.add(botonAceptar());
        this.add(botones, BorderLayout.PAGE_END);
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
                new Fig18(nombreUsuario);
            }
        });
        return cancelar;
    }

    //Método para crear el botón de aceptar, que mandará a comprobar los datos
    private JButton botonAceptar() {

        JButton aceptar = new JButton("Aceptar");
        aceptar.setFont(Font.getFont(Config.FONT));
        aceptar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JSONObject resultado = ArkanoidFrontera.getArkanoidFrontera().comprobarCambio(nombreUsuario, String.valueOf(contrasenaOriginal.getPassword()), String.valueOf(contrasena1.getPassword()), String.valueOf(contrasena2.getPassword()));

                //Comprobar el estado del cambio de contraseña
                if (!resultado.getBoolean("estado")) {
                    //Si ha sido incorrecto mostrar mensaje de error, con el mensaje que corresponda
                    new MensajeError((String) resultado.get("mensaje"), false);

                } else {
                    //Si ha sido exitoso, cerrar pantalla actual
                    cerrarActual();
                    //Abrir pantalla de Menú Principal con el nombre de usuario del jugador
                    new Fig18((String) resultado.get("mensaje"));
                }
            }
        });
        return aceptar;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
