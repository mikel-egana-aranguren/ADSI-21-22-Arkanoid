package eus.ehu.adsi.arkanoid.modelo;

public class Usuario {
    private String nombreUsuario;

    //Faltan más atributos
    public Usuario(String nombre) {
        nombreUsuario = nombre;
    }

    public boolean esNombre(String pNombreUsuario) {
        return nombreUsuario == pNombreUsuario;
    }
    
}
