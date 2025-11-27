package dao.prestamo;

import dao.ConexionBD;
import model.Prestamo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoImpl implements PrestamoDAO {
    @Override
    public void addPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO prestamo (fechaInicio, fechaFin, libroId, usuarioId) VALUES (?,?,?,?)";

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            LocalDate fechaInicio = LocalDate.parse(prestamo.getFechaInicio()); //
            LocalDate fechaFin = LocalDate.parse(prestamo.getFechaFin());       // Se hacen parse por si acaso la fecha esta de manera incorrecta

            ps.setDate(1, Date.valueOf(fechaInicio));
            ps.setDate(2, Date.valueOf(fechaFin));
            ps.setInt(3, prestamo.getIdLibro());
            ps.setInt(4, prestamo.getIdUsuario());
            ps.executeUpdate();
            System.out.println("DAO: Préstamo insertado -> " + prestamo);
        }
    }

    @Override
    public List<Prestamo> getAll() throws SQLException {
        String sql = "SELECT * FROM prestamo " +
                "ORDER BY id";
        List<Prestamo> listaPrestamos = new ArrayList<>();

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) aniadirPrestamo(rs, listaPrestamos);
        }

        return listaPrestamos;
    }

    @Override
    public void deletePrestamo(int prestamo) throws SQLException {
        String sql = "DELETE FROM prestamo WHERE id = ?";

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, prestamo);
            ps.executeUpdate();
            System.out.println("DAO: Préstamo eliminado -> " + prestamo);
        }
    }

    @Override
    public List<Prestamo> verRetrasos() throws SQLException {
        String sql = "SELECT * FROM prestamo WHERE fechaFin < CURDATE()";
        List<Prestamo> listaPrestamos = new ArrayList<>();

        try (
                Connection conn = ConexionBD.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) aniadirPrestamo(rs, listaPrestamos);
        }

        return listaPrestamos;
    }

    private static void aniadirPrestamo(ResultSet rs, List<Prestamo> listaPrestamos) throws SQLException {
        // Se le da formato a la fecha para guardarla
        Date fechaInicioDate = rs.getDate("fechaInicio");
        Date fechaFinDate = rs.getDate("fechaFin");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaInicio = (fechaInicioDate != null) ? sdf.format(fechaInicioDate) : null;
        String fechaFin = (fechaFinDate != null) ? sdf.format(fechaFinDate) : null;

        listaPrestamos.add(
                new Prestamo(
                        rs.getInt("id"),
                        fechaInicio,
                        fechaFin,
                        rs.getInt("usuarioId"),
                        rs.getInt("libroId")
                )
        );
    }
}