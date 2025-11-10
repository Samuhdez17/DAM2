package dao;

import java.sql.*;

public class ConexionBD {
	private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
	private static String USER;
	private static String PASSWORD;

	private Connection conexion;

    public ConexionBD(String usuario, String contra) {
        USER = usuario;
        PASSWORD = contra;
        try {
            conectar();
        } catch (SQLException ex) {
            System.err.println("Error al conectar con la base de datos: " + ex);
        }
    }

	private void conectar() throws SQLException {
		 conexion = DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public void desconectar() {
		try{
			if (conexion != null){
				conexion.close();
			}
		}catch (Exception error){
			System.out.println("Error porducido al desconectar la base de datos: " + error);
		}
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
			return conexion.prepareStatement(sql);
	}

	public PreparedStatement prepareStatementGK(String sql) {
		try {
			return conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ResultSet ejecutarConsulta(PreparedStatement preparedStatement) throws SQLException {
		return preparedStatement.executeQuery();
	}

	public void ejecutarActualizacion(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.executeUpdate();
	}
}