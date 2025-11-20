package dao.evento;

import model.Evento;

import java.sql.Connection;
import java.util.ArrayList;

public interface EventoDAO {
    void registrarEvento(Evento evento, Connection conexion);
    void actualizarEvento(Evento evento, Connection conexion);
    ArrayList<Evento> getEventos(Connection conexion);
    Evento getEventoPorTitulo(String titulo, Connection conexion);
    Evento getEventoPorId(int id, Connection conexion);
}
