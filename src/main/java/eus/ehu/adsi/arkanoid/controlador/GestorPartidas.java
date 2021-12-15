package eus.ehu.adsi.arkanoid.controlador;

import java.util.ArrayList;
import java.util.List;

import eus.ehu.adsi.arkanoid.modelo.Usuario;

public class GestorPartidas {
    private List<Partida> lPartidas;
    private static GestorPartidas mGestorPartidas = null; 

    private GestorPartidas() {
        lPartidas = new ArrayList<Partida>();
    }

    public static GestorPartidas getGestorPartidas() {
        if (mGestorPartidas == null) mGestorPartidas = new GestorPartidas();
        return mGestorPartidas;
    }

    public Partida buscarPartidaActual(Usuario u) {
        for (Partida p : lPartidas) {
            if (p.esUsuario(u)) 
                if (p.noFechaFin()) {
                    return p;
                }
        }
        return null;
    }
}
