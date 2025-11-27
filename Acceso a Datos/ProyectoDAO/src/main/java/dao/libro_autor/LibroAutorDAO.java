package dao.libro_autor;


import model.Libro;
import model.LibroAutor;

import java.sql.SQLException;
import java.util.List;

public interface LibroAutorDAO {
    void addLibroAutor(LibroAutor libroAutor) throws SQLException;
    List<Libro> getLibrosByAutor(int autor) throws SQLException;
    void deleteAutor(int autor) throws SQLException;
}
