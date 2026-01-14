package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmpleadoProyectoId implements Serializable {
    private static final long serialVersionUID = -4862690579740388959L;
    @Column(name = "id_empleado", nullable = false)
    private Integer idEmpleado;

    @Column(name = "id_proyecto", nullable = false)
    private Integer idProyecto;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoProyectoId entity = (EmpleadoProyectoId) o;
        return Objects.equals(this.idEmpleado, entity.idEmpleado) &&
                Objects.equals(this.idProyecto, entity.idProyecto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpleado, idProyecto);
    }
}