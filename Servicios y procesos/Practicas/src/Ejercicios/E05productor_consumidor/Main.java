package Ejercicios.E05productor_consumidor;

public class Main {
    public static void main(String[] args) {
        ColaMensajes colaMensajes = new ColaMensajes();

        for (int i = 0; i < 2; i++) {
            new Thread(new Productor(colaMensajes, i)).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Consumidor(colaMensajes, i)).start();
        }

    }
}
