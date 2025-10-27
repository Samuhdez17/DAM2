package Ejercicios.E05productor_consumidor;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase Cola
 * Esta clase es la memoria compartida entre el Productor y el Consumidor.
 * Contiene una lista Queue de mensajes para almacenarlos y la capacidad máxima
 * @see Productor
 * @see Consumidor
 * */
public class Cola {
    private final Queue<Mensaje> cola = new LinkedList<>();
    private final int capacidad = 5;

    /**
     * Método aniadirACola
     * Este método recibe un mensaje por parte del Productor, lo almacena en la cola y avisa a los hilos de lo cometido.
     * @param mensaje Mensaje generado por el Productor.
     * @see Productor#run() línea 41
     */
    public synchronized void aniadirACola(Mensaje mensaje) throws InterruptedException {
        while (cola.size() >= capacidad) wait();

        cola.add(mensaje);
        notifyAll();
    }

    /**
     * Método sacarDeCola
     * Este método manda al Consumidor el primer mensaje de la cola para que lo saque por consola y avisa a los demas hilos de lo cometido
     * @see Consumidor#run() línea 32
     * @return Mensaje generado por el Productor.
     */
    public synchronized Mensaje sacarDeCola() throws InterruptedException {
        while (cola.isEmpty()) wait();

        Mensaje mensaje = cola.poll(); //Método de la clase Queue para borrar el primer elemento de la cola
        notifyAll();
        return mensaje;
    }
}
