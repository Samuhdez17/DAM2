package Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "partidos")
public class Partido {
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_local")
    private Equipo equipoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_visitante")
    private Equipo equipoVisitante;

    @Column(name = "puntos_local")
    private Integer puntosLocal;

    @Column(name = "puntos_visitante")
    private Integer puntosVisitante;

    @Column(name = "temporada", length = 5)
    private String temporada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public Integer getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(Integer puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public Integer getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(Integer puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

}