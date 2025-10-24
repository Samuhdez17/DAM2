package Ejercicios.E05productor_consumidor;

import java.util.LinkedList;

public class ColaMensajes {
    private final LinkedList<Mensaje> cola = new LinkedList<>();

    public Mensaje leer() {
        Mensaje mensaje = cola.getFirst();
        cola.removeFirst();
        return mensaje;
    }

    public void escribir(Mensaje mensaje) {
        cola.addLast(mensaje);
    }

    public boolean hayMensajes() {
        return !cola.isEmpty();
    }
}
