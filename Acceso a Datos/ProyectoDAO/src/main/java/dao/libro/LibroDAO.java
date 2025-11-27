package dao.libro;

import model.Libro;

import java.sql.SQLException;
import java.util.List;

public interface LibroDAO {
    void addLibro(Libro libro) throws SQLException;
    List<Libro> getAllLibros() throws SQLException;
    Libro getLibroById(int id) throws SQLException;
    Libro getLibroByTitulo(String titulo) throws SQLException;
    void updateLibro(Libro libro) throws SQLException;
    void deleteLibro(int id) throws SQLException;
}
