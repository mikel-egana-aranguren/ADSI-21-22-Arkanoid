package eus.ehu.adsi.arkanoid.modelo;

public class Usuario {
    private String nombreUsuario;
    private String correo;
    private String contrasena;
    private ColorBola colorBola;
    private ColorPadel colorPadel;
    private ColorFondo colorFondo;
    private ColorLadrillos colorLadrillos;
    private AjusteSonido sonido;
    //Faltan más atributos
    public Usuario(String pNombreUsuario, String pCorreo, String pContrasena) {

        nombreUsuario = pNombreUsuario;
        correo = pCorreo;
        contrasena = pContrasena;
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

    public void cambiarAjustes(String colorBola, String colorPadel, String colorLadrillo, String colorFondo, boolean sonido) {
        this.colorBola = new ColorBola(colorBola);
        this.colorPadel = new ColorPadel(colorPadel);
        this.colorFondo = new ColorFondo(colorFondo);
        this.colorLadrillos = new ColorLadrillos(colorLadrillos);
        this.sonido = new AjusteSonido(sonido);
    }
    
    public void setContrasena(String pContrasena) {
        this.contrasena = pContrasena;
    }

    public String getNombre() {
    	return nombreUsuario;
    }
}
