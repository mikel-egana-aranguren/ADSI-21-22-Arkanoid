package eus.ehu.adsi.arkanoid.controlador;

import java.util.ArrayList;
import java.util.List;

import eus.ehu.adsi.arkanoid.modelo.Usuario;

public class GestorUsuarios {
    private static GestorUsuarios mGestorUsuarios = null; 
    private List<Usuario> lUsuarios;

    private GestorUsuarios() {
        lUsuarios = new ArrayList<Usuario>();
    }

    public static GestorUsuarios getGestorUsuarios() {
        if (mGestorUsuarios == null) mGestorUsuarios = new GestorUsuarios();
        return mGestorUsuarios;
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        for (Usuario u : lUsuarios) {
            if (u.esNombre(nombreUsuario))
                return u;
        }
        return null;
    }


    /*MÉTODOS PARA PRUEBAS*/
    public void anadir(Usuario u) {
        this.lUsuarios.add(u);
    }

    public boolean esContrasena(Usuario U, String pContrasena) {
        return U.esContrasena(pContrasena);
    }
}
