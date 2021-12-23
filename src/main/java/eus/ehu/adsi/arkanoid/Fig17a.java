package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.view.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Fig17a extends JFrame {


    public Fig17a() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setResizable(false);
        this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.setTitle("Iniciar sesión");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.createBufferStrategy(2);

    }
}
