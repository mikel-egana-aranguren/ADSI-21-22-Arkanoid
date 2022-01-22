package eus.ehu.adsi.arkanoid.core;

import java.util.List;

import org.json.JSONObject;

import eus.ehu.adsi.arkanoid.controlador.ArkanoidFrontera;
import eus.ehu.adsi.arkanoid.view.game.Ball;
import eus.ehu.adsi.arkanoid.view.game.Brick;
import eus.ehu.adsi.arkanoid.view.game.Config;
import eus.ehu.adsi.arkanoid.view.game.GameObject;
import eus.ehu.adsi.arkanoid.view.game.Paddle;
import eus.ehu.adsi.arkanoid.view.game.ScoreBoard;


public class Game {
	private boolean running;
	private boolean tryAgain;
	
	public Game () {
		this.setRunning(false);
		this.setTryAgain(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isTryAgain() {
		return tryAgain;
	}

	public void setTryAgain(boolean tryAgain) {
		this.tryAgain = tryAgain;
	}
	
	public static void testCollision(Paddle mPaddle, Ball mBall, int nivel) {
		if (!Game.isIntersecting(mPaddle, mBall))
			return;
		mBall.velocityY = -Config.getBallVelocity(nivel);
		if (mBall.x < mPaddle.x)
			mBall.velocityX = -Config.getBallVelocity(nivel);
		else
			mBall.velocityX = Config.getBallVelocity(nivel);
	}
	
	public static void testCollision(Brick mBrick, Ball mBall, ScoreBoard scoreboard, int nivel) {
		if (!Game.isIntersecting(mBrick, mBall))
			return;

		mBrick.destroyed = true;

		if (mBrick.getSuerte()) {
			JSONObject j = ArkanoidFrontera.getArkanoidFrontera().darVentaja("null");
			String descrip = j.getString("descrip");
			
			if (!j.isNull("vidas")) {
				int vidas = j.getInt("vidas");
				scoreboard.updateLives(vidas);
				System.out.println(descrip);
			} else if (!j.isNull("bola")) {
				//Arkanoid.getArkanoid().duplicarBola();
				System.out.println(descrip);
			} else if (!j.isNull("paddle")) {
				System.out.println(descrip);
			} else if (!j.isNull("ladrillos")) {
				int ladrillos = j.getInt("ladrillos");
				//Arkanoid.getArkanoid().eliminarLadrillos(ladrillos, mBrick);
				System.out.println(descrip);
			}
		}

		scoreboard.increaseScore(nivel);

		double overlapLeft = mBall.right() - mBrick.left();
		double overlapRight = mBrick.right() - mBall.left();
		double overlapTop = mBall.bottom() - mBrick.top();
		double overlapBottom = mBrick.bottom() - mBall.top();

		boolean ballFromLeft = overlapLeft < overlapRight;
		boolean ballFromTop = overlapTop < overlapBottom;

		double minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
		double minOverlapY = ballFromTop ? overlapTop : overlapBottom;

		if (minOverlapX < minOverlapY) {
			mBall.velocityX = ballFromLeft ? -Config.getBallVelocity(nivel) : Config.getBallVelocity(nivel);
		} else {
			mBall.velocityY = ballFromTop ? -Config.getBallVelocity(nivel) : Config.getBallVelocity(nivel);
		}
	}
	
	public static boolean isIntersecting(GameObject mA, GameObject mB) {
		return mA.right() >= mB.left() && mA.left() <= mB.right()
				&& mA.bottom() >= mB.top() && mA.top() <= mB.bottom();
	}
	
	public static List<Brick> initializeBricks(List<Brick> bricks, int nivel) {
		int counter = 10; //Para generar ladrillos de la suerte
		boolean suerte = false;
		bricks.clear();
		for (int iX = 0; iX < Config.getCountBlocksX(nivel); ++iX) {
			for (int iY = 0; iY < Config.getCountBlocksY(nivel); ++iY) {
				counter--;
				if (counter == 0) {
					suerte = true;
					counter = 7; //igual es más rentable hacerlo con números aleatorios para que no quede tan random xdddd
				} else suerte = false;
				bricks.add(new Brick(
						(iX + 1) * (Config.BLOCK_WIDTH + 3) + 22,
						(iY + 2) * (Config.BLOCK_HEIGHT + 3) + 50,
						suerte)
						);
			}
		}
		return bricks;
	}

}



