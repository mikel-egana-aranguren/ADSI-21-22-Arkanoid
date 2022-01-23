package eus.ehu.adsi.arkanoid;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import eus.ehu.adsi.arkanoid.core.Game;
import eus.ehu.adsi.arkanoid.view.Ball;
import eus.ehu.adsi.arkanoid.view.Brick;

public class PremiosTest {
	
	private static final Logger logger = LogManager.getLogger(PremiosTest.class);

	@Test
	public void prueba0A() {
		
	}
	
	@Test
	public void prueba0B() {

	}
	
	@Test
	public void prueba1A() {
		//Para realizar esta prueba miramos los resultados que nos da el método obtenerPremiosObtenidos un usuario sin premios
		String si = Arkanoid.obtenerPremiosObtenidos("urkazio");
		assertTrue(si.length()==0);
		//Para realizar esta prueba miramos los resultados que nos da el método obtenerPremiosNoObtenidos un usuario sin premios
		String no = Arkanoid.obtenerPremiosNoObtenidos("urkazio");
		assertTrue(no.length()!=0);
	}
	
	@Test
	public void prueba1B() {
		//Para realizar esta prueba miramos los resultados que nos da el método  obtenerPremiosObtenidos un usuario con todos los premios
		String si = Arkanoid.obtenerPremiosObtenidos("endika1");
		assertTrue(si.length()!=0);
		//Para realizar esta prueba miramos los resultados que nos da el método obtenerPremiosNoObtenidos un usuario sin premios
		String no = Arkanoid.obtenerPremiosNoObtenidos("endika1");
		assertTrue(no.length()==0);
	}
	
	//Las pruebas que se proponen 2A y 2B no ocurren => No hago los casos de prueba.
	 
	@Test
	public void prueba3A() {
		//
	}
	
	@Test
	public void prueba3B() {

	}
	
	@Test
	public void prueba4A() {

	}
	
	@Test
	public void prueba4B() {

	}

	@Test
	public void prueba5A() {

	}
	
	@Test
	public void prueba5B() {

	}

	@Test
	public void prueba5C() {

	}
	
	@Test
	public void prueba5D() {

	}
	
	@Test
	public void prueba6A() {

	}
	
	@Test
	public void prueba6B() {

	}
	
	@Test
	public void prueba7A() {

	}
	
	@Test
	public void prueba7B() {

	}
	
	@Test
	public void prueba7C() {

	}
}
