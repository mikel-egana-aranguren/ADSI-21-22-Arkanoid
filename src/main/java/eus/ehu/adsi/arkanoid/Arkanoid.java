package eus.ehu.adsi.arkanoid;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import eus.ehu.adsi.arkanoid.core.Bonus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eus.ehu.adsi.arkanoid.view.Ball;
import eus.ehu.adsi.arkanoid.view.Config;
import eus.ehu.adsi.arkanoid.view.Paddle;
import eus.ehu.adsi.arkanoid.view.ScoreBoard;
import eus.ehu.adsi.arkanoid.view.Brick;
import eus.ehu.adsi.arkanoid.core.Game;

public class Arkanoid extends JFrame implements KeyListener {

	// Housekeeping
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Arkanoid.class);

	// Game variables
	private Game game;
	private static Paddle paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
	private static Ball ball = new Ball(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
	private List<Brick> bricks = new ArrayList<Brick>();
	private static ScoreBoard scoreboard = new ScoreBoard();

	private double lastFt;
	private double currentSlice;

	private static String usuarioIniciado;

	public Arkanoid() {
		
		game = new Game ();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(false);
		this.setResizable(false);
		this.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		this.setTitle("Arkanoid");
		this.setVisible(true);
		this.addKeyListener(this);
		this.setLocationRelativeTo(null);
		this.createBufferStrategy(2);

		bricks = Game.initializeBricks(bricks,Config.Nivel_Inicio);
	}

	private Paddle getPaddle() {
		return paddle;
	}

	private ScoreBoard getScoreBoard() {
		return this.scoreboard;
	}

	void run() throws InterruptedException {

		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = bf.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		game.setRunning(true);

		while (game.isRunning()) {

			long time1 = System.currentTimeMillis();

			if(scoreboard.nivelSuperado){
				scoreboard.nivelSuperado=false;
				Game.initializeBricks(bricks,scoreboard.getNivelActual());
				paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
                ball = new Ball(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
				ball.x = Config.SCREEN_WIDTH / 2;
				ball.y = Config.SCREEN_HEIGHT / 2;
				paddle.x = Config.SCREEN_WIDTH / 2;
			}
			
			
			if (!scoreboard.gameOver && !scoreboard.win) {
				logger.info("Playing");
				game.setTryAgain(false);
				update();

				drawScene(ball, bricks, scoreboard);

				// to simulate low FPS
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				}

			} else { //HAY QUE ACTUALIZAR ESTO ESTO
				if (game.isTryAgain()) {

					logger.info("Trying again");
					game.setTryAgain(false);

					bricks = Game.initializeBricks(bricks,Config.Nivel_Inicio);

					scoreboard.lives = Config.PLAYER_LIVES;
					scoreboard.score = 0;
					scoreboard.win = false;
					scoreboard.gameOver = false;
					scoreboard.updateScoreboard();
					paddle = new Paddle(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT - 50);
					ball = new Ball(Config.SCREEN_WIDTH / 2, Config.SCREEN_HEIGHT / 2);
					ball.x = Config.SCREEN_WIDTH / 2;
					ball.y = Config.SCREEN_HEIGHT / 2;
					paddle.x = Config.SCREEN_WIDTH / 2;
				}
			}

			long time2 = System.currentTimeMillis();
			double elapsedTime = time2 - time1;

			lastFt = elapsedTime;

			double seconds = elapsedTime / 1000.0;
			if (seconds > 0.0) {
				double fps = 1.0 / seconds;
				logger.info("FPS: " + fps);
			}

		}

		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

	}

	private void update() throws InterruptedException {

		currentSlice += lastFt;

		for (; currentSlice >= Config.FT_SLICE; currentSlice -= Config.FT_SLICE) {

			ball.update(scoreboard, paddle);
			paddle.update();
			Game.testCollision(paddle, ball);

			Iterator<Brick> it = bricks.iterator();
			while (it.hasNext()) {
				Brick brick = it.next();
				Game.testCollision(brick, ball, scoreboard);
				if (brick.destroyed) {
					it.remove();
				}
			}
		}
	}

	private void drawScene(Ball ball, List<Brick> bricks, ScoreBoard scoreboard) {

		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;

		try {

			g = bf.getDrawGraphics();

			g.setColor(Config.BACKGROUND_COLOR);
			g.fillRect(0, 0, getWidth(), getHeight());

			ball.draw(g);
			paddle.draw(g);
			for (Brick brick : bricks) {
				brick.draw(g);
			}
			scoreboard.draw(g);

		} finally {
			g.dispose();
		}

		bf.show();

		Toolkit.getDefaultToolkit().sync();

	}

	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.setRunning(false);
		}
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			game.setTryAgain(true);
		}
		if (event.getKeyCode() == KeyEvent.VK_S) {
			if (scoreboard.gameOver || scoreboard.win ) {
				compartirResultado();
			}
		}
		switch (event.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			paddle.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			paddle.moveRight();
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			paddle.stopMove();
			break;
		default:
			break;
		}
	}
	public static void aplicarBonus(Bonus bonus) throws InterruptedException {
		String nomBonus=bonus.getNombre();
		if(nomBonus.equals("Mas vidas")){
			scoreboard.aumentarVidas();
		}
		else if(nomBonus.equals("Paddle grande")){
			paddle.paddleGrande();
		}
		else if(nomBonus.equals("Bola grande")){
			ball.bolaGorda();
		}

	}

	public void keyTyped(KeyEvent arg0) {}
	public void compartirResultado(){
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT * FROM partidanormal ORDER BY fecha DESC LIMIT 1;");
		String resultado = "";
		String mensaje = "";
		try {
				rs.next();
				int puntos = rs.getInt("puntos");
				int nivel = rs.getInt("numnivel");
				String usuario = rs.getString("username");
				String fechaUltima = rs.getString("fecha");
				System.out.println("SELECT * FROM partidanormal where username = '" +usuario+"' ORDER BY puntos  DESC LIMIT 1;");
				rs.close();
				ResultSet rsMax = GestorBD.miGestorBD.execSQL1("SELECT * FROM partidanormal where username ='" +usuario+"' ORDER BY puntos  DESC LIMIT 1");
				rsMax.next();
				String fechaMax = rsMax.getString("fecha");
				System.out.println(fechaMax +" ||||| "+fechaUltima);

			if(fechaMax.equals(fechaUltima)){
					mensaje="Yo, " +usuario+" conseguido una nueva Puntuacion Máxima "+puntos+" puntos en el nivel "+ nivel + " de Arkanoid ADSI!!!";
				}
				else{
					mensaje="Yo, " +usuario+" conseguido "+puntos+" puntos en el nivel "+ nivel + " de Arkanoid ADSI!!!";
				}
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				try {
					Desktop.getDesktop().browse(new URI("https://twitter.com/intent/tweet?text="+mensaje.replace( " ","%20")));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}

				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

////////////////////////////////////////////PREMIOS////////////////////////////////////////////
  
	public static String obtenerDescripciones() {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT * FROM premio");
		String resultado = "";
		try {
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String desc = rs.getString("descr");
				resultado = resultado+nombre+": "+desc+"8";
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static String obtenerPremiosObtenidos(String usuario) {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT nombre FROM premiosJugador WHERE username='" + usuario+"'");
		String resultado = "";
		try {
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				resultado = resultado+nombre+"8";
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public static String obtenerPremiosNoObtenidos(String usuario) {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT nombre FROM premio WHERE nombre NOT IN (SELECT nombre FROM premiosJugador WHERE username='" + usuario+ "')");
		String resultado = "";
		try {
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				resultado = resultado+nombre+"8";
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;

	}

	public static void entregarPremios(String usuario) {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT username, ganada FROM partidaNormal WHERE username='"+ usuario +"' ORDER BY fecha");
		int total=0;
		int racha=0;
		try {
			while (rs.next()) {
				Boolean victoria = rs.getBoolean("ganada");
				if(victoria) {
					total=total+1;
					racha=racha+1;
				}
				else {
					racha=0;
				}
				
			}
			//Una vez ya hemos visto cuantas victorias y rachas asignamos los premios correspondientes
			//VICTORIAS TOTALES
			ResultSet rs2;
			if(total>=5 && total<10) {
				rs2 = GestorBD.miGestorBD.execSQL1("SELECT * FROM premiosjugador WHERE username='"+ usuario +"' AND nombre='Bronce'");
				if(!rs2.next()) {
					GestorBD.miGestorBD.execSQL2("INSERT INTO premiosjugador VALUES('"+ usuario +"','Bronce')");
				}
			}
			else if(total>=10 && total<20) {
				rs2 = GestorBD.miGestorBD.execSQL1("SELECT * FROM premiosjugador WHERE username='"+ usuario +"' AND nombre='Plata'");
				if(!rs2.next()) {
					GestorBD.miGestorBD.execSQL2("INSERT INTO premiosjugador VALUES('"+ usuario +"','Plata')");					
				}
			}
			else if(total>=20 && total<50) {
				rs2 = GestorBD.miGestorBD.execSQL1("SELECT * FROM premiosjugador WHERE username='"+ usuario +"' AND nombre='Oro'");
				if(!rs2.next()) {
					GestorBD.miGestorBD.execSQL2("INSERT INTO premiosjugador VALUES('"+ usuario +"','Oro')");					
				}
			}
			else if(total>=50) {
				rs2 = GestorBD.miGestorBD.execSQL1("SELECT * FROM premiosjugador WHERE username='"+ usuario +"' AND nombre='Platino'");
				if(!rs2.next()) {
					GestorBD.miGestorBD.execSQL2("INSERT INTO premiosjugador VALUES('"+ usuario +"','Platino')");					
				}
			}
			
			//RACHA DE VICTORIAS
			if(total>=5 && total<10) {
				rs2 = GestorBD.miGestorBD.execSQL1("SELECT * FROM premiosjugador WHERE username='"+ usuario +"' AND nombre='Rub�'");
				if(!rs2.next()) {
					GestorBD.miGestorBD.execSQL2("INSERT INTO premiosjugador VALUES('"+ usuario +"','Rub�')");					
				}
			}
			else if(total>=10 && total<20) {
				rs2 = GestorBD.miGestorBD.execSQL1("SELECT * FROM premiosjugador WHERE username='"+ usuario +"' AND nombre='Zafiro'");
				if(!rs2.next()) {
					GestorBD.miGestorBD.execSQL2("INSERT INTO premiosjugador VALUES('"+ usuario +"','Zafiro')");					
				}
			}
			else if(total>=20) {
				rs2 = GestorBD.miGestorBD.execSQL1("SELECT * FROM premiosjugador WHERE username='"+ usuario +"' AND nombre='Diamante'");
				if(!rs2.next()) {
					GestorBD.miGestorBD.execSQL2("INSERT INTO premiosjugador VALUES('"+ usuario +"','Diamante')");					
				}
			}
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	////////////////////////////////	RANKING 	/////////////////////////////////////////	
	
	public static String obtenerRankingPA(String usuario) {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT * FROM PartidaNormal WHERE username='" + usuario+"' ORDER BY puntos ASC");
		String resultado = "";
		try {
			while (rs.next()) {
				String nlvl = rs.getString("numNivel");
				String user = rs.getString("username");
				Date fecha = rs.getDate("fecha");
				int ptos = rs.getInt("puntos");

				resultado = user+"#"+nlvl+"#"+ptos+"#"+fecha+"$";
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;

	}
	
	public static String obtenerRankingPN(String usuario, int nivel) {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT * FROM PartidaNormal WHERE username='" + usuario+"' AND numNivel='" + nivel+"' ORDER BY puntos ASC");
		String resultado = "";
		try {
			while (rs.next()) {
				String nlvl = rs.getString("numNivel");
				String user = rs.getString("username");
				Date fecha = rs.getDate("fecha");
				int ptos = rs.getInt("puntos");

				resultado = user+"#"+nlvl+"#"+ptos+"#"+fecha+"$";
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;

	}
	
	public static String obtenerRankingGA() {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT * FROM PartidaNormal ORDER BY puntos ASC");
		String resultado = "";
		try {
			while (rs.next()) {
				String nlvl = rs.getString("numNivel");
				String user = rs.getString("username");
				Date fecha = rs.getDate("fecha");
				int ptos = rs.getInt("puntos");

				resultado = user+"#"+nlvl+"#"+ptos+"#"+fecha+"$";
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;

	}
	
	public static String obtenerRankingGN(int nivel) {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT * FROM PartidaNormal WHERE AND numNivel='" + nivel+"' ORDER BY puntos ASC");
		String resultado = "";
		try {
			while (rs.next()) {
				String nlvl = rs.getString("numNivel");
				String user = rs.getString("username");
				Date fecha = rs.getDate("fecha");
				int ptos = rs.getInt("puntos");

				resultado = user+"#"+nlvl+"#"+ptos+"#"+fecha+"$";
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
          
	////////////////////////////////////////////////////REGISTRO//////////////////////////////////////////////////////
	public static void registrarse(String email, String user, String password){
		GestorBD.miGestorBD.execSQL2("INSERT INTO jugador VALUES('"+user+"','"+password+"',1,'"+email+"','verde','rojo','negro','azul');");
		iniciar(user);
	}
	public static void iniciarSesion(String usuario, String password){
		ResultSet result = GestorBD.miGestorBD.execSQL1("SELECT passwrd FROM jugador WHERE username='"+usuario+"';");
		try {
			if (result.next()){
				String pw = result.getString("passwrd");
				if (pw.equals(password)){
					iniciar(usuario);
					JOptionPane.showMessageDialog(null, "Sesion iniciada");
				}
				else{
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Usuario no encontrado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void iniciar(String user){
		usuarioIniciado = user;
	}
	public static void modificarContrasena(String user, String password){
		ResultSet result = GestorBD.miGestorBD.execSQL1("SELECT passwrd FROM jugador WHERE username='"+user+"';");
		try {
			if (result.next()){
				GestorBD.miGestorBD.execSQL2("UPDATE jugador SET passwrd='"+password+"' WHERE username='"+user+"'");
			}
			else{
				JOptionPane.showMessageDialog(null, "Usuario no encontrado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////////////// AJUSTES /////////////////////////////////////////////////////////////////
	public static String obtenerAjustes(String pUser) {
		ResultSet rs = GestorBD.miGestorBD.execSQL1("SELECT * FROM jugador WHERE username='" + pUser+"'");
		String datos = " ";
		try {
			if(rs.next()) {
				datos=rs.getString("colFondo") + " ";
				datos=datos + rs.getString("colBrick") + " ";
				datos=datos + rs.getString("colBola") + " ";
				datos=datos + rs.getString("colPaddle");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}
	
	public static void cambiarColores(String pUser,String pFondo,String pLadrillo,String pBola,String pPaddle) {
		if(pFondo.equalsIgnoreCase("Amarillo")) {
			System.out.println("Amarillo");
			Config.BACKGROUND_COLOR = Color.yellow;
		} else if(pFondo.equalsIgnoreCase("Rojo")) {
			System.out.println("Rojo");
			Config.BACKGROUND_COLOR = Color.red;
		} else if(pFondo.equalsIgnoreCase("Azul")) {
			System.out.println("Azul");
			Config.BACKGROUND_COLOR = Color.blue;
		} else if(pFondo.equalsIgnoreCase("Negro")) {
			System.out.println("Negro");
			Config.BACKGROUND_COLOR = Color.black;
		}
		
		if(pLadrillo.equalsIgnoreCase("Amarillo")) {
			System.out.println("Amarillo");
			Config.BRICK_COLOR = Color.yellow;
		} else if(pLadrillo.equalsIgnoreCase("Rojo")) {
			System.out.println("Rojo");
			Config.BRICK_COLOR = Color.red;
		} else if(pLadrillo.equalsIgnoreCase("Azul")) {
			System.out.println("Azul");
			Config.BRICK_COLOR = Color.blue;
		} else if(pLadrillo.equalsIgnoreCase("Negro")) {
			System.out.println("Negro");
			Config.BRICK_COLOR = Color.black;
		} 
		
		if(pBola.equalsIgnoreCase("Amarillo")) {
			System.out.println("Amarillo");
			Config.BALL_COLOR = Color.yellow;
		} else if(pBola.equalsIgnoreCase("Rojo")) {
			System.out.println("Rojo");
			Config.BALL_COLOR = Color.red;
		} else if(pBola.equalsIgnoreCase("Azul")) {
			System.out.println("Azul");
			Config.BALL_COLOR = Color.blue;
		} else if(pBola.equalsIgnoreCase("Negro")) {
			System.out.println("Negro");
			Config.BALL_COLOR = Color.black;
		} 
		
		if(pPaddle.equalsIgnoreCase("Amarillo")) {
			System.out.println("Amarillo");
			Config.PADDLE_COLOR = Color.yellow;
		} else if(pPaddle.equalsIgnoreCase("Rojo")) {
			System.out.println("Rojo");
			Config.PADDLE_COLOR = Color.red;
		} else if(pPaddle.equalsIgnoreCase("Azul")) {
			System.out.println("Azul");
			Config.PADDLE_COLOR = Color.blue;
		} else if(pPaddle.equalsIgnoreCase("Negro")) {
			System.out.println("Negro");
			Config.PADDLE_COLOR = Color.black;
		} 
		
		GestorBD.miGestorBD.execSQL2("UPDATE Jugador SET colFondo='" + pFondo+"', colBrick='" + pLadrillo+"', colBola='" + pBola+"', colPaddle='" + pPaddle+"' WHERE username='" + pUser+"'");
	}

	public static void cambiarContrasenaUsuarioIniciado(String password){
		modificarContrasena(usuarioIniciado,password);
	}
	public static String getUsuarioIniciado(){
		return usuarioIniciado;
	}

}







