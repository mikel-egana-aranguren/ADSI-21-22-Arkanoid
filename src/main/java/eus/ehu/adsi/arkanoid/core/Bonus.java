package eus.ehu.adsi.arkanoid.core;

import eus.ehu.adsi.arkanoid.Arkanoid;
import eus.ehu.adsi.arkanoid.view.ScoreBoard;

import java.util.Random;

public class Bonus {
    private String nombre;

    public Bonus(){
        String[] s = {"Mas vidas", "Paddle grande", "Bola grande"};

        Random ran = new Random();
        this.nombre= s[ran.nextInt(s.length)];
    }

    public String getNombre() {
        return nombre;
    }
}
