package eus.ehu.adsi.arkanoid.view;

import eus.ehu.adsi.arkanoid.core.Bonus;

import java.awt.Graphics;
import java.util.Random;

public class Brick extends Rectangle {

	public boolean destroyed = false;
	private boolean especial;
	private Bonus bonus;

	public Brick(double x, double y) {
		this.especial = getBooleanWithProbability(20);
		if (this.especial == true) {
			this.bonus = new Bonus();
		} else {
			this.bonus = null;
		}
		this.x = x;
		this.y = y;
		this.sizeX = Config.BLOCK_WIDTH;
		this.sizeY = Config.BLOCK_HEIGHT;
	}

	public void draw(Graphics g) {
		g.setColor(Config.BRICK_COLOR);
		g.fillRect((int) left(), (int) top(), (int) sizeX, (int) sizeY);
	}

	private boolean getBooleanWithProbability(int probabilityOfTrue) {
		if (probabilityOfTrue <= 0) {
			return false;
		} else {
			return new Random().nextInt(100) + 1 <= probabilityOfTrue;
		}
	}

	public boolean esEspecial() {
		return especial;
	}

	public Bonus getBonus() {
		return bonus;
	}

}