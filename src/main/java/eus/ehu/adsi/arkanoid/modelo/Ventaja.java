package eus.ehu.adsi.arkanoid.modelo;

public abstract class Ventaja {
    protected String descrip;

    public Ventaja() {}

    public abstract void darVentaja();

    public String getDescrip() {
        return descrip;
    }
}
