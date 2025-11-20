package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String USER = "root";
    private String PASSWORD = "a";
    private String URL = "jdbc:mysql://localhost:3306/examenad";
    private Connection conexion;

    public Conexion() {
        conectar();
    }

    private void conectar() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la BBDD" + e);
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
