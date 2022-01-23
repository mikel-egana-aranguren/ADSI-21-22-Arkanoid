package eus.ehu.adsi.arkanoid.view.game;

import java.awt.Graphics;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.Personalizacion21;

public class Brick extends Rectangle {

	public boolean destroyed = false;
	private boolean suerte;

	public Brick(double x, double y, boolean pSuerte) {
		this.x = x;
		this.y = y;
		this.sizeX = Config.BLOCK_WIDTH;
		this.sizeY = Config.BLOCK_HEIGHT;
		suerte = pSuerte;
	}

	public void draw(Graphics g) {
		if (suerte) g.setColor(Config.BRICK_COLOR_SUERTE);
		else g.setColor(Config.getBackGroundColor());
		g.fillRect((int) left(), (int) top(), (int) sizeX, (int) sizeY);
	}

	public boolean getSuerte() {
		return suerte;
	}

	public double getX () {
		return x;
	}

	public double getY() {
		return y;
	}
}