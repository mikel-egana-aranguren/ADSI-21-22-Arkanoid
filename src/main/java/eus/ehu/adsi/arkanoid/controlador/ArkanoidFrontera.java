package eus.ehu.adsi.arkanoid.controlador;

import java.util.Random;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.Usuario;

public class ArkanoidFrontera {
    private static ArkanoidFrontera mArkanoidFrontera = null;

    private ArkanoidFrontera() {}

    public static ArkanoidFrontera getArkanoidFrontera() {
        if (mArkanoidFrontera == null) mArkanoidFrontera = new ArkanoidFrontera();
        return mArkanoidFrontera;
    }

    public JSONObject darVentaja(String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);
        int random = generarNumeroAleatorio(4, 1);
        System.out.println(random);
        return GestorPartidas.getGestorPartidas().crearVentaja(random, u);
    }

    private int generarNumeroAleatorio(int i, int j) {
        Random r = new Random();
        return r.nextInt(i - j + 1) + j;
    }

    public JSONObject comprobarInicio(String nombreUsuario, String contrasena) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();

        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

        if (U != null) {
            correcto = GestorUsuarios.getGestorUsuarios().esContrasena(U, contrasena);

            if (!correcto) {
                resultado.put("mensaje","Contraseña incorrecta.");
            } else {
                resultado.put("mensaje", nombreUsuario);
            }
        } else {
            resultado.put("mensaje", "El usuario no está registrado.");
        }

        resultado.put("estado", correcto);

        return resultado;
    }
}
