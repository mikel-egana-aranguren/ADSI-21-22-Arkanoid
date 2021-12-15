package eus.ehu.adsi.arkanoid.controlador;

import eus.ehu.adsi.arkanoid.modelo.Ventaja;
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
            default:
                return null;    

        }
    }
}
