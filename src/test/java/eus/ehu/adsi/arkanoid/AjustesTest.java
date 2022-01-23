package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import eus.ehu.adsi.arkanoid.view.Config;

public class AjustesTest {

	@Test
	public void test0ABCD() {
		//cambiamos todos los colores a negro
		//comprobamos que han cambiado tanto en la BD como en la clase config
		Arkanoid.cambiarColores("anegda", "negro", "negro", "negro", "negro");
		assertTrue(Config.BACKGROUND_COLOR.equals(Color.black));
		assertTrue(Config.BALL_COLOR.equals(Color.black));
		assertTrue(Config.BRICK_COLOR.equals(Color.black));
		assertTrue(Config.PADDLE_COLOR.equals(Color.black));
		//cambiamos todos los colores a rojo
		//comprobamos que han cambiado tanto en la BD como en la clase config
		Arkanoid.cambiarColores("anegda", "rojo", "rojo", "rojo", "rojo");
		assertTrue(Config.BACKGROUND_COLOR.equals(Color.red));
		assertTrue(Config.BALL_COLOR.equals(Color.red));
		assertTrue(Config.BRICK_COLOR.equals(Color.red));
		assertTrue(Config.PADDLE_COLOR.equals(Color.red));
		Arkanoid.obtenerAjustes("anegda");
	}
	
//	@Test
//	public void test0B() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void test0C() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void test0D() {
//		fail("Not yet implemented");
//	}

}
