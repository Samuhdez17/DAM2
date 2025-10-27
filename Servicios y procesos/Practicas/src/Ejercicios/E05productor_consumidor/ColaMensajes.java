package Ejercicios.E05productor_consumidor;

import java.util.LinkedList;

public class ColaMensajes {
    private final LinkedList<Mensaje> cola = new LinkedList<>();

    public ColaMensajes() {
    }

    public synchronized Mensaje getMensaje() {
        while (cola.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Mensaje mensaje = cola.getFirst();
        cola.removeFirst();
        notifyAll();
        return mensaje;
    }

    public synchronized void agregarEnCola(Mensaje mensaje) {
        while (cola.size() >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cola.addLast(mensaje);
        notifyAll();
    }
}
