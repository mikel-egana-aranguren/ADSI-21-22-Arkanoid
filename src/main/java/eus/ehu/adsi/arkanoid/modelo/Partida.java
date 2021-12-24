package eus.ehu.adsi.arkanoid.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partida {
    private int puntuacion;
    private int ladrillosNormalesDestruidos;
    private int ladrillosEspecialesDestruidos;
    private boolean victoria;
    private Date fechaFin;
    private Usuario jugador;
    private List<Ventaja> listaVentajas = new ArrayList<Ventaja>();
    private int numVidas;

    public Partida(Usuario u) {
        jugador = u;
        fechaFin = null;
    }

    public boolean esUsuario(Usuario u) {
        return jugador.equals(u);
    }

    public boolean noFechaFin() {
        return fechaFin == null;
    }

    public void agregarAListaVentaja(Ventaja v) {
        listaVentajas.add(v);
    }


}
