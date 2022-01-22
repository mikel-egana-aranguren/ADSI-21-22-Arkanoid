package eus.ehu.adsi.arkanoid.view.game;

import java.awt.Graphics;

public class Ball extends GameObject {

	public double x;
	public double y;
	double radius = Config.BALL_RADIUS;
	public double velocityX = 0.2;
	public double velocityY = 0.2;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.setColor(Config.BALL_COLOR);
		g.fillOval((int) left(), (int) top(), (int) radius * 2,
				(int) radius * 2);
	}

	public void update(ScoreBoard scoreBoard, Paddle paddle, int nivel) {
		x += velocityX * Config.FT_STEP;
		y += velocityY * Config.FT_STEP;

		if (left() < 0)
			velocityX = Config.getBallVelocity(nivel);
		else if (right() > Config.SCREEN_WIDTH)
			velocityX = -Config.getBallVelocity(nivel);
		if (top() < 0) {
			velocityY = Config.getBallVelocity(nivel);
		} else if (bottom() > Config.SCREEN_HEIGHT) {
			velocityY = -Config.getBallVelocity(nivel);
			x = paddle.x;
			y = paddle.y - 50;
			scoreBoard.die();
			
		}

	}

	public double left() {
		return x - radius;
	}

	public double right() {
		return x + radius;
	}

	public double top() {
		return y - radius;
	}

	public double bottom() {
		return y + radius;
	}

}