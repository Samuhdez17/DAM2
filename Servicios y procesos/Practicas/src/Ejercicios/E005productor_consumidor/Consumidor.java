package Ejercicios.E005productor_consumidor;

/**
 * Clase Consumidor
 * Esta clase recibe el mensaje que se tiene en la cola para sacarlo por consola mediante el método FIFO
 * Contiene su ID y la cola a compartir con Productor
 */
public class Consumidor implements Runnable {
    private Cola cola;
    private int id;

    /**
     * Constructor
     * Se establece el ID del consumidor y la cola a compartir con Productor
     * @param cola Memoria compartida con Productor
     * @param id ID del consumidor
     */
    public Consumidor(Cola cola, int id) {
        this.cola = cola;
        this.id = id;
    }

    /**
     * Run
     * Este método se ejecuta al iniciar el hilo de la clase Consumidor
     * Este recibe el primer mensaje que haya en la memoria compartida de la cola para sacarlo por consola
     * @see Cola#sacarDeCola()
     */
    public void run() {
        while (true) {
            try {
                Mensaje mensaje = cola.sacarDeCola();
                System.out.printf("[C] Consumidor %d, consumió mensaje: %s\n", id, mensaje);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Se marca a sí mismo como interrumpido para poder terminar el proceso
            }
        }
    }
}
