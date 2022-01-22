package eus.ehu.adsi.arkanoid.modelo;

import org.json.JSONObject;

public class VentajaLadrillos extends Ventaja {
    private int ladrillos = 2;

    public VentajaLadrillos() {
        super();
        descrip = "Se han eliminado " + ladrillos + " ladrillos contiguos";
    }

    @Override
    public JSONObject darVentaja() {
        JSONObject j = new JSONObject();
        j.put("descrip", this.descrip);
        j.put("ladrillos", this.ladrillos);
        return j;
    }

}
