package Ejercicios.E05productor_consumidor;

public class Mensaje {
    private final int id;
    private String estado;
    private final String descripcion;

    public Mensaje(int id, String descripcion) {
        this.id = id;
        this.estado = "Listo";
        this.descripcion = descripcion;
    }
}
