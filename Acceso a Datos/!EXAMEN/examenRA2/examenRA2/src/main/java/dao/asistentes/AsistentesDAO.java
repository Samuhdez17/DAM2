package dao.asistentes;

import model.Asistente;

import java.sql.Connection;
import java.util.ArrayList;

public interface AsistentesDAO {
    ArrayList<Asistente> getAsistentesPorEvento(int id, Connection conexion);
    void registrarAsistente(Asistente asistente, Connection conexion);
    void eliminarAsistente(int asistente, Connection conexion);
}
