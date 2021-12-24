package eus.ehu.adsi.arkanoid.modelo;

import org.json.JSONObject;

public abstract class Ventaja {
    protected String descrip;

    public Ventaja() {}

    public abstract JSONObject darVentaja();
}
