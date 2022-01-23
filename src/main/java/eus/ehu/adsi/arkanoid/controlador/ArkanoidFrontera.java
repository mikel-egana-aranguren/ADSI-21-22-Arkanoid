package eus.ehu.adsi.arkanoid.controlador;

import java.sql.SQLException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.xml.crypto.Data;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.core.ArkanoidThread;
import eus.ehu.adsi.arkanoid.modelo.DataBase;
import eus.ehu.adsi.arkanoid.modelo.Usuario;
import eus.ehu.adsi.arkanoid.view.game.Config;

public class ArkanoidFrontera {
    private static ArkanoidFrontera mArkanoidFrontera = null;
    private LocalDateTime fechaHoraInicio;
    private String fechaHoraInicioStr;
    private int lvl;
    private String nombreUsuario;

    private ArkanoidFrontera() {}

    public static ArkanoidFrontera getArkanoidFrontera() {
        if (mArkanoidFrontera == null) mArkanoidFrontera = new ArkanoidFrontera();
        return mArkanoidFrontera;
    }

    public String getNombre(){
        return nombreUsuario;
    }

    public JSONObject darVentaja(String nombreUsuario) {
        Usuario u = GestorUsuarios.getGestorUsuarios().buscarUsuario(nombreUsuario);
        int random = generarNumeroAleatorio(4, 1);
        return GestorPartidas.getGestorPartidas().crearVentaja(random, u);
    }

    private int generarNumeroAleatorio(int i, int j) {
        Random r = new Random();
        return r.nextInt(i - j + 1) + j;
    }

    public void cambiarAjustes(String colorBola, String colorPadel, String colorLadrillo, String colorFondo, Boolean sonido, String nombreUsuario) {
        try {
            DataBase.getmDataBase().cambiarAjustes(nombreUsuario, colorBola, colorPadel, colorLadrillo, colorFondo, sonido);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Config.setBackgroundColor(ArkanoidFrontera.getArkanoidFrontera().getColor("Fondo", ArkanoidFrontera.getArkanoidFrontera().getNombre()));
		Config.setLadrilloColor(ArkanoidFrontera.getArkanoidFrontera().getColor("Ladrillo", ArkanoidFrontera.getArkanoidFrontera().getNombre()));
		Config.setBolaColor(ArkanoidFrontera.getArkanoidFrontera().getColor("Bola", ArkanoidFrontera.getArkanoidFrontera().getNombre()));
		Config.setPaddleColor(ArkanoidFrontera.getArkanoidFrontera().getColor("Paddle", ArkanoidFrontera.getArkanoidFrontera().getNombre()));
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
                System.err.println(e);
                return null;
            }
        } else {
            try {
                return DataBase.getmDataBase().jugadorPosIndividual(pos, jugador, nivel);
            } catch (SQLException e) {
                System.err.println(e);
                return null;
            }
        }
    }

    public int nPartidas(String jugador, int nivel) {
        if (jugador == null){
            try {
				return DataBase.getmDataBase().nPartidasGlobal(nivel);
			} catch (SQLException e) {
                System.err.println(e);
				return 0;
			}
        }else{
            try {
                return DataBase.getmDataBase().nPartidasIndividual(jugador, nivel);
            } catch (SQLException e) {
                System.err.println(e);
                return 0;
            }
        }
    }

    public void comenzarPartida(int nivel){
        //DataBase.getmDataBase().crearPartida(nivel, nombreJugador);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime fechaHoraInicio = LocalDateTime.now();
        fechaHoraInicioStr = dtf.format(fechaHoraInicio);
        lvl = nivel;
        ArkanoidThread arkanoidThread = new ArkanoidThread();
        arkanoidThread.start();
    }

    public int getLvl(){
        return lvl;
    }

    public JSONObject cargarDatosPersonalizacion(String nombre) {
        try {
            nombreUsuario = nombre;
            return DataBase.getmDataBase().cargarDatosPersonalizacion(nombre);
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public JSONObject getColores(String objeto, String nombre){
        JSONObject colores;
        if (objeto == "fondo"){
            try {
                colores = DataBase.getmDataBase().getColoresFondo(nombre);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                return null;
            }
        } else if (objeto == "bola"){
            try {
                colores = DataBase.getmDataBase().getColoresBola(nombre);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                return null;
            }
        }else if (objeto == "paddle"){
            try {
                colores = DataBase.getmDataBase().getColoresPaddle(nombre);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                return null;
            }
        }else {
            try {
                colores = DataBase.getmDataBase().getColoresLadrillos(nombre);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                return null;
            }
        }
        return colores;
    }

    public Color getColor(String obj, String nombre) {
        String colorStr = null;
        try {
            colorStr = DataBase.getmDataBase().getColor(obj, nombre);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        Color color;
        switch (colorStr) {
            case "Rojo":
                color = Color.RED;
                break;
            case "Verde":
                color = Color.GREEN;
                break;
            case "Morado":
                color = Color.MAGENTA;
                break;
            case "Naranja":
                color = Color.ORANGE;
                break;
            case "Cyan":
                color = Color.CYAN;
                break;
            case "Blanco":
                color = Color.WHITE;
                break;
            case "Azul":
                color = Color.BLUE;
                break;
            case "Negro":
                color = Color.BLACK;
                break;
            default:
                color = Color.PINK;
                break;
        }
        return color;
    }
}
