package eus.ehu.adsi.arkanoid;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.Fig16;

public class App {

	public static void main(String[] args) {
		//new Arkanoid(1).run();
		ArkanoidFrontera.getArkanoidFrontera().comprobarRegistro("usuario", "jon.garbe5@gmail.com", "contrasena", "contrasena");
		new Fig16();
	}
}
