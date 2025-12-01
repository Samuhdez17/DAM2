package Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "estadisticas")
public class Estadistica {
    @EmbeddedId
    private EstadisticaId id;

    @MapsId("jugador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jugador", nullable = false)
    private Jugadore jugador;

    @Column(name = "Puntos_por_partido")
    private Float puntosPorPartido;

    @Column(name = "Asistencias_por_partido")
    private Float asistenciasPorPartido;

    @Column(name = "Tapones_por_partido")
    private Float taponesPorPartido;

    @Column(name = "Rebotes_por_partido")
    private Float rebotesPorPartido;

    public EstadisticaId getId() {
        return id;
    }

    public void setId(EstadisticaId id) {
        this.id = id;
    }

    public Jugadore getJugador() {
        return jugador;
    }

    public void setJugador(Jugadore jugador) {
        this.jugador = jugador;
    }

    public Float getPuntosPorPartido() {
        return puntosPorPartido;
    }

    public void setPuntosPorPartido(Float puntosPorPartido) {
        this.puntosPorPartido = puntosPorPartido;
    }

    public Float getAsistenciasPorPartido() {
        return asistenciasPorPartido;
    }

    public void setAsistenciasPorPartido(Float asistenciasPorPartido) {
        this.asistenciasPorPartido = asistenciasPorPartido;
    }

    public Float getTaponesPorPartido() {
        return taponesPorPartido;
    }

    public void setTaponesPorPartido(Float taponesPorPartido) {
        this.taponesPorPartido = taponesPorPartido;
    }

    public Float getRebotesPorPartido() {
        return rebotesPorPartido;
    }

    public void setRebotesPorPartido(Float rebotesPorPartido) {
        this.rebotesPorPartido = rebotesPorPartido;
    }

}