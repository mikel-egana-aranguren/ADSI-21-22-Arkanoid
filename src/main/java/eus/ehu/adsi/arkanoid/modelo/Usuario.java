package eus.ehu.adsi.arkanoid.modelo;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String correo;

    //Faltan más atributos
    public Usuario(String pNombreUsuario, String pContrasena, String pCorreo) {

        nombreUsuario = pNombreUsuario;
        contrasena = pContrasena;
        correo = pCorreo;
    }

    public boolean esNombre(String pNombreUsuario) {
        return nombreUsuario.equals(pNombreUsuario);
    }

    public boolean esCorreo(String pCorreo) {
        return pCorreo.equals(correo);
    }
    
    public boolean esContrasena(String pContrasena) {
        return pContrasena.equals(contrasena);
    }

    public void setContrasena(String pContrasena) {
        this.contrasena = pContrasena;
    }
}
