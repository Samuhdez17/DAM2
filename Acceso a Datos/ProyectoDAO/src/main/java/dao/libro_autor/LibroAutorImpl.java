package dao.libro_autor;

import dao.ConexionBD;
import model.Libro;
import model.LibroAutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroAutorImpl implements LibroAutorDAO {
    @Override
    public void addLibroAutor(LibroAutor libroAutor) throws SQLException {
        String sql = "INSERT INTO libro_autor (idLibro, idAutor) VALUES (?,?)";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, libroAutor.getIdLibro());
            ps.setInt(2, libroAutor.getIdAutor());
            ps.executeUpdate();
            System.out.printf("DAO: Relación insertada -> Libro = %d ; Autor = %d\n", libroAutor.getIdLibro(), libroAutor.getIdAutor());
        }
    }

    public List<Libro> getLibrosByAutor(int idAutor) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT l.id, titulo, isbn FROM libro l " +
                "JOIN libro_autor la ON l.id = la.idLibro " +
                "JOIN autor a ON la.idAutor = a.id " +
                "WHERE idAutor = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAutor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                libros.add(new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("isbn")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    @Override
    public void deleteAutor(int id) throws SQLException {
        String sql = "DELETE FROM libro_autor WHERE idAutor = ?";

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("DAO: Libros del autor eliminado -> " + id);
        }
    }
}