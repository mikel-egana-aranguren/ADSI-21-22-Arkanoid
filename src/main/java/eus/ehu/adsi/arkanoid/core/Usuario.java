package eus.ehu.adsi.arkanoid.core;

public class Usuario {

    private String contrasena;




    public boolean esContrasena(String pContrasena) {
        return pContrasena.equals(contrasena);
    }
}
