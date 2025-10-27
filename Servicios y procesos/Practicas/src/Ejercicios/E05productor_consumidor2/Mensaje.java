package Ejercicios.E05productor_consumidor2;

public class Mensaje {
    private int id;
    private String mensaje;
    private String estado;

    public Mensaje(int id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
        this.estado = "Listo";
    }

    public int getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return String.format("Mensaje: %d: %s, estado: %s", id, mensaje, estado);
    }
}
