package model;

public class Prestamo {
    private int id;

    private String fechaInicio;
    private String fechaFin;
    private int idLibro;
    private int idUsuario;

    public Prestamo(int id, String fechaInicio, String fechaFin, int usuarioId, int libroId) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idLibro = libroId;
        this.idUsuario = usuarioId;
    }

    public Prestamo(String fechaInicio, String fechaFin, int libroId, int usuarioId) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idLibro = libroId;
        this.idUsuario = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
