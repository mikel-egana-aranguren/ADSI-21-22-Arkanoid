package eus.ehu.adsi.arkanoid.modelo;

import org.json.JSONObject;

public class VentajaPaddle extends Ventaja{
    
    public VentajaPaddle() {
        super();
        descrip = "Ha aumentado el tamaño del paddle";
    }

    @Override
    public JSONObject darVentaja() {
        JSONObject j = new JSONObject();
        j.put("descrip", this.descrip);
        j.put("paddle", true);
        return j;
    }
}
