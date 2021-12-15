package eus.ehu.adsi.arkanoid.controlador;

import java.util.Random;

import eus.ehu.adsi.arkanoid.modelo.Usuario;

public class ArkanoidC {
    private static ArkanoidC mArkanoidC = null;

    private ArkanoidC() {}

    public static ArkanoidC getArkanoidC() {
        if (mArkanoidC == null) mArkanoidC = new ArkanoidC();
        return mArkanoidC;
    }

    public String darVentaja(String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuario().buscarUsuario(nombreUsuario);
        Partida p = GestorPartidas.getGestorPartidas().buscarPartidaActual(u);
        int random = generarNumeroAleatorio(1, 4);
        return p.crearVentaja(random);
    }

    private int generarNumeroAleatorio(int i, int j) {
        Random r = new Random();
        return r.nextInt(i + j) + j;
    }
}
