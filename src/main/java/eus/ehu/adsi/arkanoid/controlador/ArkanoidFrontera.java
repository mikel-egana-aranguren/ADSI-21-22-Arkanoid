package eus.ehu.adsi.arkanoid.controlador;

import java.util.Random;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.Usuario;

public class ArkanoidFrontera {
    private static ArkanoidFrontera mArkanoidFrontera = null;

    private ArkanoidFrontera() {}

    public static ArkanoidFrontera getArkanoidFrontera() {
        if (mArkanoidFrontera == null) mArkanoidFrontera = new ArkanoidFrontera();
        return mArkanoidFrontera;
    }

    public JSONObject darVentaja(String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);
        int random = generarNumeroAleatorio(4, 1);
        System.out.println(random);
        return GestorPartidas.getGestorPartidas().crearVentaja(random, u);
    }

    private int generarNumeroAleatorio(int i, int j) {
        Random r = new Random();
        return r.nextInt(i - j + 1) + j;
    }

    public void cambiarAjustes(String colorBola, String colorPadel, String colorLadrillo, String colorFondo, boolean sonido, String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);
        u.cambiarAjustes(colorBola, colorPadel, colorLadrillo, colorFondo, sonido);
    }
}
