package Ejercicios.E05productor_consumidor2;

import java.util.LinkedList;
import java.util.Queue;

public class Cola {
    private final Queue<Mensaje> cola = new LinkedList<>();
    private final int capacidad = 5;

    public synchronized void aniadirACola(Mensaje mensaje) throws InterruptedException {
        while (cola.size() >= capacidad) wait();

        cola.add(mensaje);
        notifyAll();
    }

    public synchronized Mensaje sacarDeCola() throws InterruptedException {
        while (cola.isEmpty()) wait();

        Mensaje mensaje = cola.poll(); //
        notifyAll();
        return mensaje;
    }
}
