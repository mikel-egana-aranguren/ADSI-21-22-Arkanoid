package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.view.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fig17c extends JFrame {

    private String correo;
    private String codigo;
    private JTextField codigoIntroducido;
    private JTextField contrasena1;
    private JTextField contrasena2;

    public Fig17c(String pCorreo, String pCodigo) {

        this.correo = pCorreo;
        this.codigo = pCodigo;
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
        contrasena1 = new JTextField("", 10);
        this.add(contrasena1);

        JLabel textoContrasena2 = new JLabel("Confirmar contraseña:");
        textoContrasena2.setForeground(Config.FONT_COLOR);
        this.add(textoContrasena2);
        contrasena2 = new JTextField("", 10);
        this.add(contrasena2);

        JLabel textoVolver = new JLabel("¿No te ha llegado el código?:");
        textoVolver.setForeground(Config.FONT_COLOR);
        this.add(textoVolver);
        this.add(botonVolver());

        this.add(botonAceptar());
        this.add(botonCancelar());
    }

    private JButton botonVolver() {

        JButton olvidar = new JButton("Volver a enviar");
        olvidar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Volver a enviar");
                //Enviar correo
                new Fig17c("", "");
            }
        });
        return olvidar;
    }

    private JButton botonAceptar() {

        JButton cancelar = new JButton("Aceptar");
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Aceptar");
                //Comprobar
                new Fig17a();
            }
        });
        return cancelar;
    }

    private JButton botonCancelar() {

        JButton olvidar = new JButton("Cancelar");
        olvidar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Cancelar");
                new Fig17b();
            }
        });
        return olvidar;
    }
}
