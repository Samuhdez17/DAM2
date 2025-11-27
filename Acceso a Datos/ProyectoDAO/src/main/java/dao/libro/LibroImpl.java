package dao.libro;

import dao.ConexionBD;
import model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroImpl implements LibroDAO {
    @Override
    public void addLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO libro (titulo, isbn) VALUES (?, ?)";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) libro.setId(rs.getInt(1));
            }

            System.out.println("DAO: Libro insertado -> " + libro);
        }
    }

    @Override
    public List<Libro> getAllLibros() throws SQLException {
        String sql = "SELECT id, titulo, isbn FROM libro " +
                "ORDER BY titulo";
        List<Libro> lista = new ArrayList<>();

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                lista.add(new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("isbn")));
            }
        }
        return lista;
    }

    @Override
    public Libro getLibroById(int id) throws SQLException {
        String sql = "SELECT id, titulo, isbn FROM libro WHERE id = ?";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("isbn"));
                }
            }
        }
        return null;
    }

    @Override
    public Libro getLibroByTitulo(String titulo) throws SQLException {
        String sql = "SELECT id, titulo, isbn FROM libro WHERE titulo = ?";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, titulo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("isbn"));
                }
            }
        }
        return null;
    }

    @Override
    public void updateLibro(Libro libro) throws SQLException {
        String sql = "UPDATE libro SET titulo = ? WHERE id = ?";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, libro.getTitulo());
            ps.setInt(2, libro.getId());
            ps.executeUpdate();
            System.out.println("DAO: Libro actualizado -> " + libro);
        }
    }

    @Override
    public void deleteLibro(int id) throws SQLException {
        String sql = "DELETE FROM libro WHERE id = ?";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("DAO: Libro eliminado (id = " + id + ")");
        }
    }
}