package eus.ehu.adsi.arkanoid;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;

public class GestorBD {
	public static GestorBD miGestorBD = new GestorBD();
	
	private GestorBD(){
	}
	
	public GestorBD getMiGestorBD() {
		return GestorBD.miGestorBD;
	}
	
	public ResultSet execSQL1(String instr) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery(instr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void execSQL2(String instr) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery(instr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}