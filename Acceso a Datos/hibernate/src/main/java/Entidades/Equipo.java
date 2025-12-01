package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipos")
public class Equipo {
    @Id
    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "Ciudad", length = 20)
    private String ciudad;

    @Column(name = "Conferencia", length = 4)
    private String conferencia;

    @Column(name = "Division", length = 9)
    private String division;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

}