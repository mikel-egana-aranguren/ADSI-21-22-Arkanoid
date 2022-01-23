package eus.ehu.adsi.arkanoid;


import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
//No puedo hacer asserts en muchas de las pruebas porque las salidas son por ventas con texto.
public class IdentificacionTest {

    @Test
    public void registrarse() throws SQLException {
        //campos vacios
        Arkanoid.registrarse("","","");
        Arkanoid.registrarse("","prueba1","");
        //nick que existe
        Arkanoid.registrarse("123@prueba.com","endika1","123");
        //formato mail incorrecto
        Arkanoid.registrarse("123prueba.com","prueba2","123");
        //mail existente
        Arkanoid.registrarse("endika@ehu.eus","prueba4","123");
        //registro correcto
        Arkanoid.registrarse("12345@prueba.com","prueba5","123");
        ResultSet result = GestorBD.miGestorBD.execSQL1("SELECT * FROM jugador WHERE username='prueba5'");
        assertTrue(result.next());
        assertEquals(result.getString("username"),"prueba5");
        assertEquals(result.getString("email"),"12345@prueba.com");
        assertEquals(result.getString("passwrd"),"123");
        GestorBD.miGestorBD.execSQL2("DELETE FROM jugador WHERE username='prueba5';");
    }

    @Test
    public void iniciarSesion() {
        //campos vacios
        Arkanoid.iniciarSesion("","");
        //usuario inexistente
        Arkanoid.iniciarSesion("no_existo","123");
        //contraseña incorrecta
        Arkanoid.iniciarSesion("endika1","123");
        //inicio correcto
        Arkanoid.iniciarSesion("endika1","contrasena");
        assertEquals(Arkanoid.getUsuarioIniciado(),"endika1");
    }

    @Test
    public void modificarContrasena() throws SQLException {
        //campos vacios
        Arkanoid.modificarContrasena("","");
        //usuario inexistente
        Arkanoid.modificarContrasena("no_existo","123");
        //usuario correcto
        Arkanoid.modificarContrasena("endika1","123");
        ResultSet result = GestorBD.miGestorBD.execSQL1("SELECT passwrd FROM jugador WHERE username='endika1';");
        assertTrue(result.next());
        assertEquals(result.getString("passwrd"),"123");
        Arkanoid.modificarContrasena("endika1","contrasena");
        result = GestorBD.miGestorBD.execSQL1("SELECT passwrd FROM jugador WHERE username='endika1';");
        assertTrue(result.next());
        assertEquals(result.getString("passwrd"),"contrasena");
    }

    @Test
    public void cambiarContrasenaUsuarioIniciado() throws SQLException {
        Arkanoid.iniciar("endika1");
        //campos vacios
        Arkanoid.cambiarContrasenaUsuarioIniciado("");
        //misma contraseña
        Arkanoid.cambiarContrasenaUsuarioIniciado("contrasena");
        ResultSet result = GestorBD.miGestorBD.execSQL1("SELECT passwrd FROM jugador WHERE username='endika1';");
        assertTrue(result.next());
        assertEquals(result.getString("passwrd"),"contrasena");
        //usuario correcto
        Arkanoid.cambiarContrasenaUsuarioIniciado("123");
        result = GestorBD.miGestorBD.execSQL1("SELECT passwrd FROM jugador WHERE username='endika1';");
        assertTrue(result.next());
        assertEquals(result.getString("passwrd"),"123");
        Arkanoid.cambiarContrasenaUsuarioIniciado("contrasena");
        result = GestorBD.miGestorBD.execSQL1("SELECT passwrd FROM jugador WHERE username='endika1';");
        assertTrue(result.next());
        assertEquals(result.getString("passwrd"),"contrasena");
    }
}