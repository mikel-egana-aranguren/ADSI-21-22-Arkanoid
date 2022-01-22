package eus.ehu.adsi.arkanoid.view.game;

import java.awt.Color;

public abstract class Config {

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	public static final double BALL_RADIUS = 10.0;
	public static final double BALL_VELOCITY1 = 0.2;
	public static final double BALL_VELOCITY2 = 0.3;
	public static final double BALL_VELOCITY3 = 0.4;

	public static final double PADDLE_WIDTH = 60.0;
	public static final double PADDLE_HEIGHT = 20.0;
	public static final double PADDLE_VELOCITY = 0.4;

	public static final double BLOCK_WIDTH = 60.0;
	public static final double BLOCK_HEIGHT = 20.0;

	public static final int COUNT_BLOCKS_X1 = 11;
	private static final int COUNT_BLOCKS_Y1 = 2;
	private static final int COUNT_BLOCKS_X2 = 11;
	private static final int COUNT_BLOCKS_Y2 = 4;
	private static final int COUNT_BLOCKS_X3 = 11;
	private static final int COUNT_BLOCKS_Y3 = 6;

	public static final int PLAYER_LIVES = 5;

	public static final double FT_SLICE = 1.0;
	public static final double FT_STEP = 1.0;

	public static final String FONT = "Courier New";
	public static final Color FONT_COLOR = Color.lightGray;

	public static final Color BACKGROUND_COLOR = Color.black;
	public static final Color BALL_COLOR = Color.orange;
	public static final Color PADDLE_COLOR = Color.red;
	public static final Color BRICK_COLOR = Color.blue;

  public static final Color BRICK_COLOR_SUERTE = Color.yellow;


	public static double getBallVelocity(int nivel) {
		if (nivel==1)
			return BALL_VELOCITY1;
		else if (nivel==2)
			return BALL_VELOCITY2;
		else if (nivel==3)
			return BALL_VELOCITY3;
		else
			return 0;
	}

	public static double getCountBlocksX(int nivel) {
		if (nivel==1)
			return COUNT_BLOCKS_X1;
		else if (nivel==2)
			return COUNT_BLOCKS_X2;
		else if (nivel==3)
			return COUNT_BLOCKS_X3;
		else
			return 0;
	}

	public static double getCountBlocksY(int nivel) {
		if (nivel==1)
			return COUNT_BLOCKS_Y1;
		else if (nivel==2)
			return COUNT_BLOCKS_Y2;
		else if (nivel==3)
			return COUNT_BLOCKS_Y3;
		else
			return 0;
	}
}
