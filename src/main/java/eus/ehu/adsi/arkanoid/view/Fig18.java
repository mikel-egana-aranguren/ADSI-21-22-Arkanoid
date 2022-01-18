package eus.ehu.adsi.arkanoid.view;

import eus.ehu.adsi.arkanoid.view.game.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fig18 extends JFrame {

    private String nombreUsuario;

    public Fig18(String pNombreUsuario) {

        this.nombreUsuario = pNombreUsuario;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Menú principal");
        drawScene();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void drawScene() {

        this.getContentPane().setBackground(Config.BACKGROUND_COLOR);
        this.setLayout(new FlowLayout());

        JLabel textoNombre = new JLabel(nombreUsuario);
        textoNombre.setForeground(Config.FONT_COLOR);
        this.add(textoNombre);

        this.add(botonJugar());
        this.add(botonRanking());
        this.add(botonAjustes());
        this.add(botonCerrar());
        this.add(botonCambiar());
    }

    private JButton botonJugar() {

        JButton jugar = new JButton("JUGAR");
        jugar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cerrarActual();
                new SeleccionarNivel19();
            }
        });
        return jugar;
    }

    private JButton botonRanking() {

        JButton ranking = new JButton("Ranking");
        ranking.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //cerrarActual();
                System.out.println("Ranking, añadir fig");
            }
        });
        return ranking;
    }

    private JButton botonAjustes() {

        JButton ajustes = new JButton("Ajustes");
        ajustes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //cerrarActual();
                System.out.println("Ajustes, añadir fig");
            }
        });
        return ajustes;
    }

    private JButton botonCerrar() {

        final JButton cerrar = new JButton("Cerrar sesión");
        cerrar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //cerrarActual();
                System.out.println("Cerrar sesión, añadir fig");
            }
        });
        return cerrar;
    }

    private JButton botonCambiar() {

        JButton cambiar = new JButton("Cambiar contraseña");
        cambiar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cerrarActual();
                new CambiarContrasena20(nombreUsuario);
            }
        });
        return cambiar;
    }

    private void cerrarActual() {
        this.dispose();
    }
}
