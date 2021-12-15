package eus.ehu.adsi.arkanoid.modelo;

public class Usuario {
    private String nombreUsuario;

    //Falta constructora y más atributos

    public boolean esNombre(String pNombreUsuario) {
        return nombreUsuario == pNombreUsuario;
    }
    
}
