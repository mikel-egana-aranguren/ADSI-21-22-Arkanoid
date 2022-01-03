package eus.ehu.adsi.arkanoid.modelo;

import org.json.JSONObject;

public class VentajaBola extends Ventaja {
    
    public VentajaBola() {
        super();
        descrip = "Se ha dividido la bola en dos";
    }

    @Override
    public JSONObject darVentaja() {
        JSONObject j = new JSONObject();
        j.put("descrip", this.descrip);
        j.put("bola", true);
        return j;
    }
}
