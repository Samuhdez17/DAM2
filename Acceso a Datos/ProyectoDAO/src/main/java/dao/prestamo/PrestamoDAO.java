package dao.prestamo;

import model.Prestamo;

import java.sql.SQLException;
import java.util.List;

public interface PrestamoDAO {
    void addPrestamo(Prestamo prestamo) throws SQLException;
    List<Prestamo> getAll() throws SQLException;
    void deletePrestamo(int prestamo) throws SQLException;
    List<Prestamo> verRetrasos() throws SQLException;
}
