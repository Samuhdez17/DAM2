package services;

import dao.Conexion;
import dao.asistentes.AsistentesDAO;
import dao.evento.EventoDAO;
import model.Asistente;
import model.Evento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gestionador {
    private final Connection conexion;
    private AsistentesDAO asistentesDAO;
    private EventoDAO eventoDAO;

    public Gestionador(Conexion conexion) {
        this.conexion = conexion.getConexion();
    }

    public void aniadirEvento(String titulo, String fecha, int aforo) {
        Evento evento = new Evento(titulo, fecha, aforo);

        eventoDAO.registrarEvento(evento, conexion);
    }

    public void actualizarEvento(int id, String titulo, String fecha, int aforo) {
        Evento evento = new Evento(titulo, fecha, aforo);

        eventoDAO.actualizarEvento(evento, conexion);
    }

    public ArrayList<Evento> getEventos() {
        return eventoDAO.getEventos(conexion);
    }

    public Evento getEventoPorTitulo(String titulo) {
        return eventoDAO.getEventoPorTitulo(titulo, conexion);
    }

    public Evento getEventoPorID(int id) {
        return eventoDAO.getEventoPorId(id, conexion);
    }

    public ArrayList<Asistente> getAsistentesPorEvento(int id) {
        return asistentesDAO.getAsistentesPorEvento(id, conexion);
    }

    public void aniadirAsistente(String nombre, String email, int id) {
        Asistente asistente = new Asistente(nombre, email, id);

        asistentesDAO.registrarAsistente(asistente, conexion);
    }

    public void eliminarAsistente(int asistente) {
        asistentesDAO.eliminarAsistente(asistente, conexion);
    }

    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
}
