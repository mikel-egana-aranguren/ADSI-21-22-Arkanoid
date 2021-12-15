package eus.ehu.adsi.arkanoid.controlador;

import java.util.Date;
import java.util.List;

import eus.ehu.adsi.arkanoid.modelo.Ventaja;
import eus.ehu.adsi.arkanoid.modelo.Usuario;

public class Partida {
    private int puntuacion;
    private int ladrillosNormalesDestruidos;
    private int ladrillosEspecialesDestruidos;
    private boolean victoria;
    private Date fechaFin;
    private Usuario jugador;
    private List<Ventaja> listaVentajas;
    private int numVidas;

    public Partida() {

    }

    public boolean esUsuario(Usuario u) {
        return jugador.equals(u);
    }

    public boolean noFechaFin() {
        return fechaFin == null;
    }

    public String crearVentaja(int random) {
        Ventaja v = CreadorVentaja.getCreadorVentaja().crearVentaja(random);
        v.darVentaja();
        return v.getDescrip();
    }


}
