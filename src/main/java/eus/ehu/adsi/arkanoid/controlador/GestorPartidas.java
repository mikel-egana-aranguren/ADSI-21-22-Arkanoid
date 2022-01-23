package eus.ehu.adsi.arkanoid.controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.DataBase;
import eus.ehu.adsi.arkanoid.modelo.Partida;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import eus.ehu.adsi.arkanoid.modelo.Ventaja;

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

    public JSONObject crearVentaja(int random, Usuario u) {
        Partida p = buscarPartidaActual(u);
        Ventaja v = CreadorVentaja.getCreadorVentaja().crearVentaja(random);
        p.agregarAListaVentaja(v);
        return v.darVentaja();
    }

    public void anadir(Partida p) {
        this.lPartidas.add(p);
    }

   public int obtenerMaxPuntUsuario(String nombreUsuario) {
        int maxPunt=0;
    	try {
            maxPunt=DataBase.getmDataBase().getMaxPunt(nombreUsuario);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e);
        }
        return maxPunt;
    }

    public void crearPartida(Usuario u, int lvl) {
        Partida p = new Partida(0, 0, 0, false, u, 0, lvl);
        this.lPartidas.add(p);
    }
}
