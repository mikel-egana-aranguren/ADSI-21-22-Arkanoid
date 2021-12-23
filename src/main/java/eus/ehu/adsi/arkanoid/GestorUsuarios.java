package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.core.Usuario;

public class GestorUsuarios {


    public Usuario buscarUsuario(String nombreUsuario) {
        System.out.println("Devuelve NULL");
        return null;
    }

    public boolean esContrasena(Usuario U, String pContrasena) {
        return U.esContrasena(pContrasena);
    }
}
