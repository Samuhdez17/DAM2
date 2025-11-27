package dao;

import java.sql.*;

public class ConexionBD {
	private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
	private static String USER = "root";
	private static String PASSWORD = "a";

	private static Connection conexion;

	public static Connection getConexion() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.err.println("Error al conectar con la base de datos: " + ex);
        }
        return conexion;
	}

	public void desconectar() {
		try {
			if (conexion != null){
				conexion.close();
			}
		} catch (Exception error){
			System.out.println("Error porducido al desconectar la base de datos: " + error);
		}
	}
}