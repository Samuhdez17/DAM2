package Ejercicios.E005productor_consumidor;

/**
 * Clase Mensaje
 * Esta clase almacena el contenido generado por el productor correspondiente.
 * Esta misma tiene su propio ID identificatorio
 */
public class Mensaje {
    private int id;
    private String mensaje;
    private String estado;

    /**
     * Constructor
     * Se obtiene por parámetros el ID y contenido del mensaje
     * Se establece el estado del mensaje como "Listo"
     * @param id ID del mensaje
     * @param mensaje Contenido del mensaje
     */
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
        return String.format("[M] Mensaje %d; contenido: %s", id, mensaje);
    }
}
