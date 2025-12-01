package Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "jugadores")
public class Jugadore {
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer id;

    @Column(name = "Nombre", length = 30)
    private String nombre;

    @Column(name = "Procedencia", length = 20)
    private String procedencia;

    @Column(name = "Altura", length = 4)
    private String altura;

    @Column(name = "Peso")
    private Integer peso;

    @Column(name = "Posicion", length = 5)
    private String posicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Nombre_equipo")
    private Equipo nombreEquipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(Equipo nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

}