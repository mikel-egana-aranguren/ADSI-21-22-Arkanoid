package eus.ehu.adsi.arkanoid.view;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class ScoreBoard {

	public int score = 0;
	public int bricksRotos = 0;
	public int lives = Config.PLAYER_LIVES;
	public static int nivelActual = Config.Nivel_Inicio;
	public boolean win = false;
	public boolean gameOver = false;
	public boolean nivelSuperado = false;

	String text = "";

	Font font;

	public ScoreBoard() {
		font = new Font(Config.FONT, Font.PLAIN, 12);
		text = "Welcome to Arkanoid";
	}

	public void increaseScore() {
		score++;
		bricksRotos++;
		System.out.println("Score: " + score + " bricksRotos:" + bricksRotos);
		switch(nivelActual){
			case 1:
				if(bricksRotos==Config.NBricks1){
					nextLevel();
				}else{updateScoreboard();}
				break;
			case 2:
				if(bricksRotos==Config.NBricks2){
					nextLevel();
				}else{updateScoreboard();}
				break;
			case 3:
				if(bricksRotos==Config.NBricks3){
					win = true;
					text = "You have won! \nYour score was: " + score
							+ "\n\nPress Enter to restart + \n\nPress S to share";
				}else{updateScoreboard();}
				break;
		}
	}

	public void nextLevel(){
		bricksRotos=0;
		nivelActual++;
		nivelSuperado = true;
	}
	public static int getNivelActual(){
		return nivelActual;
	}

	void die() {
		lives--;
		if (lives == 0) {
			gameOver = true;
			text = "You have lost! \nYour score was: " + score
					+ "\n\nPress Enter to restart + \n\nPress S to share";
		} else {
			updateScoreboard();
		}
	}
	public void aumentarVidas(){lives++;}

	public void updateScoreboard() {
		text = "Score: " + score + "  Lives: " + lives;
	}

	public void draw(Graphics g) {
		if (win || gameOver) {
			font = font.deriveFont(50f);
			FontMetrics fontMetrics = g.getFontMetrics(font);
			g.setColor(Config.FONT_COLOR);
			g.setFont(font);
			int titleHeight = fontMetrics.getHeight();
			int lineNumber = 1;
			for (String line : text.split("\n")) {
				int titleLen = fontMetrics.stringWidth(line);
				g.drawString(line, (Config.SCREEN_WIDTH / 2) - (titleLen / 2),
						(Config.SCREEN_HEIGHT / 4) + (titleHeight * lineNumber));
				lineNumber++;

			}
		} else {
			font = font.deriveFont(34f);
			FontMetrics fontMetrics = g.getFontMetrics(font);
			g.setColor(Config.FONT_COLOR);
			g.setFont(font);
			int titleLen = fontMetrics.stringWidth(text);
			int titleHeight = fontMetrics.getHeight();
			g.drawString(text, (Config.SCREEN_WIDTH / 2) - (titleLen / 2),
					titleHeight + 35);
		}
	}
}
