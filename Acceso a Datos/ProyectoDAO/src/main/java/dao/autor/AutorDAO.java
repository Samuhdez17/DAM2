package dao.autor;

import model.Autor;

import java.sql.SQLException;
import java.util.List;

public interface AutorDAO {
    void addAutor(Autor autor) throws SQLException;
    List<Autor> getAllAutores() throws SQLException;
    Autor getAutorById(int id) throws SQLException;
    Autor getAutorByNombre(String autor) throws SQLException;
    void updateAutor(Autor autor) throws SQLException;
    void deleteAutor(int id) throws SQLException;
}
