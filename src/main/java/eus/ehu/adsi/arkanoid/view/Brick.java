package eus.ehu.adsi.arkanoid.view;

import java.awt.Graphics;

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
		else g.setColor(Config.BRICK_COLOR);
		g.fillRect((int) left(), (int) top(), (int) sizeX, (int) sizeY);
	}

	public boolean getSuerte() {
		return suerte;
	}
}