package entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "empleado_proyecto")
public class EmpleadoProyecto {
    @EmbeddedId
    private EmpleadoProyectoId id;

    @MapsId("idEmpleado")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado idEmpleado;

    @MapsId("idProyecto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto idProyecto;

    @Column(name = "horas", nullable = false)
    private Integer horas;

    public EmpleadoProyectoId getId() {
        return id;
    }

    public void setId(EmpleadoProyectoId id) {
        this.id = id;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

}