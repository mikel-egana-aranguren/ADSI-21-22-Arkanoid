package eus.ehu.adsi.arkanoid.controlador;

import eus.ehu.adsi.arkanoid.modelo.Ventaja;
import eus.ehu.adsi.arkanoid.modelo.VentajaBola;
import eus.ehu.adsi.arkanoid.modelo.VentajaLadrillos;
import eus.ehu.adsi.arkanoid.modelo.VentajaPaddle;
import eus.ehu.adsi.arkanoid.modelo.VentajaVidas;

public class CreadorVentaja {
    private static CreadorVentaja mCreadorVentaja;

    private CreadorVentaja() {}
    
    public static CreadorVentaja getCreadorVentaja() {
        if (mCreadorVentaja == null) mCreadorVentaja = new CreadorVentaja();
        return mCreadorVentaja;
    }

    public Ventaja crearVentaja(int random) {
        switch(random) {
            case 1:
                return new VentajaVidas();
            case 2:
                return new VentajaBola();
            case 3:
                return new VentajaPaddle();
            case 4:
                return new VentajaLadrillos();
            default:
                return null;    

        }
    }
}
