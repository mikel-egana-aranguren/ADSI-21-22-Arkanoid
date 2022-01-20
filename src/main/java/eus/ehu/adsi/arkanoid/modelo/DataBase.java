package eus.ehu.adsi.arkanoid.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DataBase {
    
    private static DataBase mDataBase;

    private DataBase(){}

    public static DataBase getmDataBase() {
        if (mDataBase == null){
            mDataBase = new DataBase();
        }
        return mDataBase;
    }

    public List<String> jugadorPosGlobal(int p, int nivel) throws SQLException{
        List<String> jugadorPuntuacion = Arrays.asList("nombre", "puntuacion");
        Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql446695","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs1;    
		if (nivel == 0){
            rs1 = s.executeQuery("SELECT nombreUsuario, puntuacion FROM partida ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }else{
            rs1 = s.executeQuery("SELECT nombreUsuario, puntuacion FROM partida WHERE nivel=\""+ nivel + "\" ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }
        jugadorPuntuacion.set(0, rs1.getString(1));
        jugadorPuntuacion.set(1, Integer.toString(rs1.getInt(2)));
        return jugadorPuntuacion;
    }

    public String jugadorPosIndividual(int p, String nombre, int nivel) throws SQLException{
        String puntuacion = "0";
        Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql446695","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs1;
        if (nivel == 0){
            rs1 = s.executeQuery("SELECT puntuacion FROM partida WHERE nombreUsuario=\""+ nombre +"\" ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }else{
            rs1 = s.executeQuery("SELECT puntuacion FROM partida WHERE nombreUsuario=\""+ nombre +"\" AND nivel=\""+ nivel +"\" ORDER BY puntuacion DESC, fechaFin ASC LIMIT 1 OFFSET " + (p-1));
        }
        puntuacion = Integer.toString(rs1.getInt(1));
        return puntuacion;
    }

    public int nPartidasGlobal(int nivel) throws SQLException{
        Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql446695","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs1;
        if (nivel == 0){
		    rs1 = s.executeQuery("SELECT count(*) FROM partida WHERE victoria=1");
        } else {
            rs1 = s.executeQuery("SELECT count(*) FROM partida WHERE victoria=1 AND nivel=\""+nivel+"\"");
        }
        return rs1.getInt(1);
    }

    public int nPartidasIndividual(String nombre, int nivel) throws SQLException{
        Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql4466495", "sql446695","NKihfwtwiR");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al registrar el dirver de MySQL:" + e);
		}
        Statement s = con.createStatement();
        ResultSet rs1;
        if (nivel == 0){
		    rs1 = s.executeQuery("SELECT count(*) FROM partida WHERE victoria=1 AND nombreUsuario=\"" + nombre + "\"");
        }else{
            rs1 = s.executeQuery("SELECT count(*) FROM partida WHERE victoria=1 AND nombreUsuario=\"" + nombre + "\" AND nivel=\""+ nivel +"\"");
        }
        return rs1.getInt(1);
    }
}