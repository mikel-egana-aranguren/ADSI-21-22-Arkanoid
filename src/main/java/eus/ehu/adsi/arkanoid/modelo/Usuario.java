package eus.ehu.adsi.arkanoid.modelo;

public class Usuario {
    private String nombreUsuario;
    private String correo;
    private String contrasena;

    //Faltan más atributos
    public Usuario(String pNombreUsuario, String pCorreo, String pContrasena) {

        nombreUsuario = pNombreUsuario;
        correo = pCorreo;
        contrasena = pContrasena;
    }

    public boolean esNombre(String pNombreUsuario) {
        return nombreUsuario.equals(pNombreUsuario);
    }

    /**
     * Comprobar si el correo proporcionado es del usuario
     * @param pCorreo correo a comprobar si es propio
     * @return true si es su correo | false sino
     */
    public boolean esCorreo(String pCorreo) {
        return pCorreo.equals(correo);
    }

    /**
     * Comprobar si la contraseña proporcionada es del usuario
     * @param pContrasena contraseña a comprobar si es la propia
     * @return true si es su contraseña | false sino
     */
    public boolean esContrasena(String pContrasena) {
        return pContrasena.equals(contrasena);
    }

    /**
     * Cambiar la contraseña actual
     * @param pContrasena contraseña nueva
     */
    public void setContrasena(String pContrasena) {
        this.contrasena = pContrasena;
    }
}
