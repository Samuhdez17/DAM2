package dao.autor;

import dao.ConexionBD;
import model.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorImpl implements AutorDAO {
    @Override
    public void addAutor(Autor autor) {
        String sql = "INSERT INTO autor (nombre) VALUES (?)";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, autor.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) autor.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            throw  new RuntimeException(ex);
        }

        System.out.println("DAO: Autor insertado -> " + autor);
    }

    @Override
    public List<Autor> getAllAutores() {
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM autor";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Autor(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Autor getAutorById(int id) {
        String sql = "SELECT id, nombre FROM autor WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Autor(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Autor getAutorByNombre(String nombre) throws SQLException {
        String sql = "SELECT id, nombre FROM autor WHERE nombre = ?";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Autor(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }

    @Override
    public void updateAutor(Autor autor) throws SQLException {
        String sql = "UPDATE autor SET nombre = ? WHERE id = ?";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, autor.getNombre());
            ps.setInt(2, autor.getId());
            ps.executeUpdate();
            System.out.println("DAO: Autor actualizado -> " + autor);
        }
    }

    @Override
    public void deleteAutor(int id) throws SQLException {
        String sql = "DELETE FROM autor WHERE id = ?";
        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("DAO: Autor eliminado -> id = " + id);
        }
    }
}
