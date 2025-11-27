package dao.usuario;

import dao.ConexionBD;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioImpl implements  UsuarioDAO {
    @Override
    public void addUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nombre) VALUES (?)";

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, usuario.getNombre());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Usuario> getAll() throws SQLException {
        String sql = "SELECT * FROM usuario " +
                "ORDER BY nombre";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) listaUsuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre")));
        }

        return listaUsuarios;
    }

    @Override
    public Usuario getUsuarioById(int usuario) {
        String sql = "SELECT id, nombre FROM usuario WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nombre = ? WHERE id = ?";

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();
            System.out.println("DAO: Usuario actualizado -> " + usuario);
        }
    }

    @Override
    public void deleteUsuario(int usuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, usuario);
            ps.executeUpdate();
            System.out.printf("DAO: Usuario eliminado -> id = %d\n", usuario);
        }
    }
}