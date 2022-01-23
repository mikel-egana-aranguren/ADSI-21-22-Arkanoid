package eus.ehu.adsi.arkanoid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/arkanoid";
	private static final String USUARIO = "root";
	private static final String CLAVE = "password";

	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexi�n OK");

		} catch (SQLException e) {
			System.out.println("Error en la conexi�n");
			e.printStackTrace();
		}
		
		return conexion;
	}
}