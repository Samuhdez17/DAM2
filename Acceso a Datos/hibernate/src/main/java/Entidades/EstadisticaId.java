package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EstadisticaId implements Serializable {
    private static final long serialVersionUID = -4271792403277838190L;
    @Column(name = "temporada", nullable = false, length = 5)
    private String temporada;

    @Column(name = "jugador", nullable = false)
    private Integer jugador;

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public Integer getJugador() {
        return jugador;
    }

    public void setJugador(Integer jugador) {
        this.jugador = jugador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EstadisticaId entity = (EstadisticaId) o;
        return Objects.equals(this.jugador, entity.jugador) &&
                Objects.equals(this.temporada, entity.temporada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jugador, temporada);
    }

}