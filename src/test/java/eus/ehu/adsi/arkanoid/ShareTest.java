package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.core.Game;
import eus.ehu.adsi.arkanoid.view.Ball;
import eus.ehu.adsi.arkanoid.view.Brick;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShareTest {
	
	private static final Logger logger = LogManager.getLogger(ShareTest.class);

	@Test
	public void compartirGanada() {
		try {
			new Arkanoid().run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(true);
		System.out.println("Comprobar si tras jugar una partida y Ganarla es posible compartir el resultado en Twitter.");
	}
	
	@Test
	public void compartirPerdida() {
		try {
			new Arkanoid().run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(true);
		System.out.println("\n\n################################################################################################");
		System.out.println("Comprobar si tras jugar una partida y perderla es posible compartir el resultado en Twitter.");
		System.out.println("################################################################################################¡\n\n");
	}
}
