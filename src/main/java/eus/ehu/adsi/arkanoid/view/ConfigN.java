package eus.ehu.adsi.arkanoid.view;

import java.awt.Color;

public abstract class ConfigN {

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	public static final double BALL_RADIUS = 10.0;
	private static final double BALL_VELOCITY1 = 0.1;
	private static final double BALL_VELOCITY2 = 0.3;
	private static final double BALL_VELOCITY3 = 0.5;

	public static final double PADDLE_WIDTH = 60.0;
	public static final double PADDLE_HEIGHT = 20.0;
	public static final double PADDLE_VELOCITY = 0.4;

	public static final double BLOCK_WIDTH = 60.0;
	public static final double BLOCK_HEIGHT = 20.0;

	public static final int COUNT_BLOCKS_X1 = 11;
	private static final int COUNT_BLOCKS_Y1 = 4;
	private static final int COUNT_BLOCKS_X2 = 11;
	private static final int COUNT_BLOCKS_Y2 = 5;
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

	public double getBallVelocity(int nivel){
		switch (nivel){
			case 1: return BALL_VELOCITY1;
			case 2: return BALL_VELOCITY2;
			case 3: return BALL_VELOCITY3;
			default: return 0;
		}
	}

	public double getCountBlocksX(int nivel){
		switch (nivel){
			case 1: return COUNT_BLOCKS_X1;
			case 2: return COUNT_BLOCKS_X2;
			case 3: return COUNT_BLOCKS_X3;
			default: return 0;
		}
	}

	public double getCountBlocksY(int nivel){
		switch (nivel){
			case 1: return COUNT_BLOCKS_Y1;
			case 2: return COUNT_BLOCKS_Y2;
			case 3: return COUNT_BLOCKS_Y3;
			default: return 0;
		}
	}
}
