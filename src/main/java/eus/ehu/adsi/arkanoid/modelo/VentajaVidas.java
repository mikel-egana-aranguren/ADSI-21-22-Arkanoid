package eus.ehu.adsi.arkanoid.modelo;

import org.json.JSONObject;

public class VentajaVidas extends Ventaja {
    private int vidas = 2;

    public VentajaVidas() {
        super();
        descrip = "Se han agregado " + vidas + " vidas";
    }

    @Override
    public JSONObject darVentaja() {
        JSONObject j = new JSONObject();
        j.put("descrip", this.descrip);
        j.put("vidas", this.vidas);
        return j;
    }
    
}
