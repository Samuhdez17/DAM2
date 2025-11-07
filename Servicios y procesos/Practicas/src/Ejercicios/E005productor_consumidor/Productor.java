package Ejercicios.E005productor_consumidor;

/**
 * Clase Productor
 * Esta clase comparte memoria con la clase Consumidor mediante la clase Cola
 * Productor genera un mensaje, lo guarda en Cola y lo recibe el Consumidor
 * Esta clase contiene su ID y la cola a compartir con Consumidor
 * @see Cola
 * @see Consumidor
 */
public class Productor implements  Runnable {
    private final Cola cola;
    private final int id;

    /**
     * Constructor
     * Se asigna el ID del Productor y la cola a compartir con Consumidor
     * @param cola Memoria compartida con Consumidor
     * @param id ID del Productor
     */
    public Productor(Cola cola, int id) {
        this.cola = cola;
        this.id = id;
    }

    /**
     * Run
     * Este método se ejecuta al iniciar el hilo de la clase Productor
     * Este genera un objeto de la clase Mensaje con un ID y un contenido.
     * En este caso el contenido es el ID del productor que ha generado el mensaje.
     * Una vez creado el objeto mensaje, el objeto cola lo almacena para poder compartirlo con el consumidor correspondiente
     * @see Cola#aniadirACola(Mensaje)
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int idMensaje = i + id * 10;
            Mensaje mensaje = new Mensaje(idMensaje, "[P] Productor: " + id);

            try {
                cola.aniadirACola(mensaje);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Se marca a sí mismo como interrumpido para poder terminar el proceso
            }
        }
    }

}
