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
    private Font impact = AddFont.createFont();

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

    //Método para dibujar el contenido de la pantalla
    private void drawScene() {
        this.setLayout(new BorderLayout());

        JPanel campos = new JPanel();
        campos.setBackground(Config.BACKGROUND_COLOR);
        campos.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoNombre = new JLabel("Nombre:");
        textoNombre.setFont(impact.deriveFont(20.0f));
        textoNombre.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 0;
        campos.add(textoNombre, c);
        nombre = new JTextField("", 20);
        c.gridx = 1;
        c.gridy = 0;
        campos.add(nombre,c);

        JLabel textoContrasena = new JLabel("Contraseña:");
        textoContrasena.setFont(impact.deriveFont(20.0f));
        textoContrasena.setForeground(Config.FONT_COLOR);
        c.gridx = 0;
        c.gridy = 1;
        campos.add(textoContrasena, c);
        contrasena = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 1;
        campos.add(contrasena, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        campos.add(botonOlvidar(), c);

        this.add(campos, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Config.BACKGROUND_COLOR);
        botones.add(botonCancelar());
        botones.add(botonIniciar());
        this.add(botones, BorderLayout.PAGE_END);
    }

    //Método para crear el botón de contrasena olvidada, que abrirá la pantalla de Recuperación
    private JButton botonOlvidar() {

        JButton olvidar = new JButton("He olvidado mi contraseña");
        olvidar.setFont(impact.deriveFont(20.0f));
        olvidar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cerrarActual();
                new CorreoRecuperacion17b();
            }
        });
        return olvidar;
    }

    //Método para crear el botón de cancelar, que abrirá la pantalla de Inicio
    private JButton botonCancelar() {

        JButton cancelar = new JButton("Cancelar");
        cancelar.setFont(impact.deriveFont(20.0f));
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

    //Método para crear el botón de iniciar sesión, que mandará a comprobar los datos
    private JButton botonIniciar() {

        JButton iniciar = new JButton("Iniciar Sesión");
        iniciar.setFont(impact.deriveFont(20.0f));
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

                //Comprobar el estado del inicio de sesión
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
        return iniciar;
    }

    //Método para poder cerrar la pantalla actual, útil cuando se trata con clases anónimas
    private void cerrarActual() {
        this.dispose();
    }
}
