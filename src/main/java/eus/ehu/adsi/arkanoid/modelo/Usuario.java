package eus.ehu.adsi.arkanoid.modelo;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;

    //Faltan más atributos
    public Usuario(String nombre) {
        nombreUsuario = nombre;
    }

    public boolean esNombre(String pNombreUsuario) {
        return nombreUsuario == pNombreUsuario;
    }
    
    public boolean esContrasena(String pContrasena) {
        return pContrasena.equals(contrasena);
    }
}
