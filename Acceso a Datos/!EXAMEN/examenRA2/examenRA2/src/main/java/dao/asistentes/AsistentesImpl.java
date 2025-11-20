package dao.asistentes;

import model.Asistente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsistentesImpl implements AsistentesDAO {
    @Override
    public ArrayList<Asistente> getAsistentesPorEvento(int id, Connection conexion) {
        ArrayList<Asistente> asistentes = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM asistentes WHERE id_asistente = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                asistentes.add(new Asistente(rs.getString("nombre"), rs.getString("email"), rs.getInt("id_evento")));
            }
        } catch (SQLException e) {
        }

        return asistentes;
    }

    @Override
    public void registrarAsistente(Asistente asistente, Connection conexion) {
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO asistentes (nombre, email, id_evento) VALUES (?,?,?)");
            ps.setString(1, asistente.getNombre());
            ps.setString(2, asistente.getEmail());
            ps.setInt(3, asistente.getEvento());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminarAsistente(int asistente, Connection conexion) {
        try {
            PreparedStatement ps = conexion.prepareStatement("DELETE FROM asistentes WHERE id = ?");
            ps.setInt(1, asistente);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
