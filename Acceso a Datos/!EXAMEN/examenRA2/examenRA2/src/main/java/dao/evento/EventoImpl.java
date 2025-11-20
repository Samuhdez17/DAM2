package dao.evento;

import model.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventoImpl implements EventoDAO {
    @Override
    public void registrarEvento(Evento evento, Connection conexion) {
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO eventos (titulo, fecha, aforo_max) VALUES (?,?,?)");
            ps.setString(1, evento.getTitulo());
            ps.setString(2, evento.getFecha());
            ps.setInt(3, evento.getAforoMax());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarEvento(Evento evento, Connection conexion) {
        try {
            PreparedStatement ps = conexion.prepareStatement("UPDATE eventos SET  titulo = ?, fecha = ?, aforo_max = ? WHERE id = ?");
            ps.setString(1, evento.getTitulo());
            ps.setString(2, evento.getFecha());
            ps.setInt(3, evento.getAforoMax());
            ps.setInt(4, evento.getID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Evento> getEventos(Connection conexion) {
        return null;
    }

    @Override
    public Evento getEventoPorTitulo(String titulo, Connection conexion) {
        return null;
    }

    @Override
    public Evento getEventoPorId(int id, Connection conexion) {
        return null;
    }
}
