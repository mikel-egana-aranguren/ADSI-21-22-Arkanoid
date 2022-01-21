package eus.ehu.adsi.arkanoid.controlador;

import java.sql.SQLException;
import java.util.Random;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.modelo.DataBase;
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

    public JSONObject recuperarContrasena(String correo) {

        JSONObject resultado = new JSONObject();

        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

        if (U != null) {

            resultado.put("mensaje", enviarEmail(correo));

        } else {
            resultado.put("mensaje", "El usuario no está registrado.");
        }

        resultado.put("estado", U != null);

        return resultado;
    }

    public String enviarEmail(String correo) {
        //TODO
        return "CODIGO";
    }

    public JSONObject comprobarCodigo(String correo, String codigo, String codigoIntroducido, String cNueva1, String cNueva2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();
        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

        if (U != null) {

            if (codigo.equals(codigoIntroducido)) {

                if (cNueva1.equals(cNueva2)) {

                    GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                    correcto = true;

                } else {
                    resultado.put("mensaje", "Las contraseñas no coinciden.");
                }
            } else {
                resultado.put("mensaje", "Código incorrecto.");
            }
        } else {
            resultado.put("mensaje", "El usuario no está registrado.");
        }

        resultado.put("estado", correcto);
        return resultado;
    }

    public JSONObject comprobarRegistro(String nombreUsuario, String correo, String contrasena1, String contrasena2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();
        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

        if (U == null) {

            U = GestorUsuarios.getGestorUsuarios().buscarUsuarioCorreo(correo);

            if (U == null) {

                if (contrasena1.equals(contrasena2)) {

                    GestorUsuarios.getGestorUsuarios().registrarUsuario(nombreUsuario, correo, contrasena1);
                    correcto = true;
                    resultado.put("mensaje", nombreUsuario);
                } else {
                    resultado.put("mensaje", "Las contraseñas no coinciden.");
                }
            } else {
                resultado.put("mensaje", "Correo no disponible.");
            }
        } else {
            resultado.put("mensaje", "Nombre de usuario no disponible.");
        }
        resultado.put("estado", correcto);

        return resultado;
    }

    public JSONObject comprobarCambio(String nombreUsuario, String cAnterior, String cNueva1, String cNueva2) {

        boolean correcto = false;
        JSONObject resultado = new JSONObject();
        Usuario U = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);

        if (GestorUsuarios.getGestorUsuarios().esContrasena(U, cAnterior)) {

            if (cNueva1.equals(cNueva2)) {
                GestorUsuarios.getGestorUsuarios().cambiarContrasena(U, cNueva1);
                correcto = true;
                resultado.put("mensaje", nombreUsuario);
            } else {
                resultado.put("mensaje", "Las contraseñas no coinciden.");
            }
        } else {
            resultado.put("mensaje", "Contraseña incorrecta.");
        }
        resultado.put("estado", correcto);

        return resultado;
    }

    public JSONObject jugadorPos(int pos, int nivel, String jugador){
        if (jugador == null){
            try {
				return DataBase.getmDataBase().jugadorPosGlobal(pos, nivel);
			} catch (SQLException e) {
				System.out.println("Error ArkanoidFrontera.jugadorPos()");
                return null;
            }
        } else {
            try {
                return DataBase.getmDataBase().jugadorPosIndividual(pos, jugador, nivel);
            } catch (SQLException e) {
                System.out.println("Error ArkanoidFrontera.jugadorPosIndividual");
                return null;
            }
        }
    }

    public int nPartidas(String jugador, int nivel) {
        if (jugador == null){
            try {
				return DataBase.getmDataBase().nPartidasGlobal(nivel);
			} catch (SQLException e) {
				return 0;
			}
        }else{
            try {
                return DataBase.getmDataBase().nPartidasIndividual(jugador, nivel);
            } catch (SQLException e) {
                return 0;
            }
        }
    }
}
