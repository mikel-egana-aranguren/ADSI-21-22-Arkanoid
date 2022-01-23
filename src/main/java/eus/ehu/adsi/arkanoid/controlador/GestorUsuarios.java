package eus.ehu.adsi.arkanoid.controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.DataBase;
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

    public Usuario buscarUsuarioCorreo(String correo) {
        for (Usuario u : lUsuarios) {
            if (u.esCorreo(correo))
                return u;
        }
        return null;
    }

    public boolean esContrasena(Usuario U, String contrasena) {
        return U.esContrasena(contrasena);
    }

    public void cambiarContrasena(Usuario U, String contrasena) {
        U.setContrasena(contrasena);
    }

    public void registrarUsuario(String nombreUsuario, String correo, String contrasena1) {
        Usuario U = new Usuario(nombreUsuario, correo, contrasena1);
        this.lUsuarios.add(U);
    }

    /*MÉTODOS PARA PRUEBAS*/
    public void anadir(Usuario u) {
        this.lUsuarios.add(u);
    }
    /////
    public JSONObject cargarDatosPersonalizacion(String nombreUsuario) {
        try {
            return DataBase.getmDataBase().cargarDatosPersonalizacion(nombreUsuario);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

   }
